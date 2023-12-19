package com.hjong.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjong.entity.ExceptionCodeMsg;
import com.hjong.entity.User;
import com.hjong.entity.WebException;
import com.hjong.entity.vo.EmailResetVO;
import com.hjong.entity.vo.UserInfoVo;
import com.hjong.mapper.UserMapper;
import com.hjong.service.IUserService;
import com.hjong.util.MailUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    MailUtils mailUtils;
    @Override
    public User loginByEmail(User user){
        if(!this.existsUserByEmail(user.getEmail())){
            throw new WebException(ExceptionCodeMsg.Email_NOT_EXISTS);
        }
        User loginUser = this.query().eq("email",user.getEmail()).one();

        if (!loginUser.getPassword().equals(user.getPassword())){
           throw new WebException(ExceptionCodeMsg.INVALID_PassWord_OR_Email);
        }else {
            loginUser.setPassword(null);
            return loginUser;
        }
    }

    @Override
    public User registerByEmail(User user, Map<String, Object> map) {

        //还未获取验证码
        if(map.get("code")==null){
            throw new WebException(ExceptionCodeMsg.Code_NOT_EXISTS);
        }
        //邮箱已经注册过，不能再次注册
        if(this.existsUserByEmail(user.getEmail())){
            throw new WebException(ExceptionCodeMsg.Email_EXISTS);
        }
        if(map.get("UserCode").equals(map.get("code")) && user.getEmail().equals(map.get("email"))){

            if(user.getUserName() !=null && user.getEmail() !=null && user.getPassword()!=null)  {
                this.save(user);
                return user;
            }else {
                throw  new WebException(ExceptionCodeMsg.INVALID_Register);
            }
        }
        return null;
    }

    @Override
    public String registerEmailVerifyCode(String email,int code) {
        if (mailUtils.sendVerifyCode(email,code)){
            return "发送成功";
        }else {
            throw new WebException(ExceptionCodeMsg.GET_CODE_Fail);
        }
    }

    @Override
    public String resetPasswordByEmail(EmailResetVO vo){
        if (vo.getCode() == null){
            throw new WebException(ExceptionCodeMsg.Code_NOT_EXISTS);
        }
        if(vo.getCode().equals(vo.getUserCode())){
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id", vo.getUserId())
                    .set("password", vo.getPassword());

            if(this.baseMapper.update(null, updateWrapper)>0){
                return "重置成功";
            }else {
                throw new WebException(ExceptionCodeMsg.Reset_FAIL);
            }
        }else {
            throw new WebException(ExceptionCodeMsg.INVALID_CODE);
        }

    }

    @Override
    public Integer updateByInfo(UserInfoVo vo) {
        if(vo == null){
            throw new WebException(ExceptionCodeMsg.FAIL_USER_ACTION);
        }
        User user = new User();
        System.out.println(vo.getIntroduction());
        user.setUserId(vo.getUser_id());
        user.setAvatar(vo.getAvatar());
        user.setUserName(vo.getUser_name());
        user.setIntroduction(vo.getIntroduction());
        return this.getBaseMapper().updateById(user);
    }

    @Override
    public String resetConfirm() {
        return null;
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return this.baseMapper.exists(Wrappers.<User>query().eq("email", email));
    }

    //判断是不是邮箱地址
    private boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
