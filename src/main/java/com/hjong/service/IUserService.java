package com.hjong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjong.entity.User;
import com.hjong.entity.vo.EmailResetVO;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
public interface IUserService extends IService<User> {
    User loginByEmail(User user);
    User registerByEmail(User user, Map<String, Object> map);
    String registerEmailVerifyCode(String email,int code);

    String resetPasswordByEmail(EmailResetVO vo);

    String resetConfirm();
    boolean existsUserByEmail(String email);
}
