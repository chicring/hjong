package com.hjong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjong.entity.*;
import com.hjong.entity.vo.ShareCreateVo;
import com.hjong.entity.vo.ShareFileVO;
import com.hjong.mapper.FileMapper;
import com.hjong.mapper.SharefilesMapper;
import com.hjong.mapper.UserMapper;
import com.hjong.service.ISharefilesService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
@Service
public class SharefilesServiceImpl extends ServiceImpl<SharefilesMapper, Sharefiles> implements ISharefilesService {

    @Resource
    UserMapper userMapper;
    @Resource
    FileMapper fileMapper;
    @Override
    public Sharefiles createShare(ShareCreateVo vo) {
        try {
            if(vo.getFile_id() != null){
                Sharefiles sharefiles = new Sharefiles();
                sharefiles.setFileId(vo.getFile_id());
                sharefiles.setShareLink(this.generateLink());
                if (vo.getUser_id() != null){
                    sharefiles.setUserId(vo.getUser_id());
                }
                if (vo.getIs_private()){
                    sharefiles.setPassword(vo.getPassword());
                }
                if (vo.getExpire_time() > 0 && vo.getExpire_time() <48){
                    sharefiles.setExpireTime(this.generateExpiryTime(vo.getExpire_time()));
                }else {
                    throw new WebException(ExceptionCodeMsg.FAIL_SHARE);
                }
                this.save(sharefiles);
                return sharefiles;
            }else {
                throw new WebException(ExceptionCodeMsg.FAIL_SHARE);
            }
        }catch (Exception e){
            throw new WebException(ExceptionCodeMsg.FAIL_SHARE);
        }

    }

    @Override
    public Sharefiles Share(String password, Integer expire_time, Integer file_id,Integer user_id) {
        try{
            Sharefiles share = new Sharefiles();
            share.setFileId(file_id);
            share.setExpireTime(this.generateExpiryTime(Objects.requireNonNullElse(expire_time, 6)));
            if(user_id != null){
                share.setUserId(user_id);
            }
            if(password != null){
                share.setPassword(password);
                share.setIsPrivate(true);
            }else {
                share.setIsPrivate(false);
            }
            share.setShareLink(this.generateLink());
            this.save(share);
            return share;
        }catch (Exception e){
            throw new WebException(ExceptionCodeMsg.FAIL_SHARE);
        }
    }

    @Override
    public Boolean deleteShare(Integer shareId) {
        return removeById(shareId);
    }

    @Override
    public IPage<ShareFileVO> findAllById(Integer userId, Integer current) {

        QueryWrapper<ShareFileVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sf.user_id",userId).orderByDesc("share_time");

        Page<ShareFileVO> page = new Page<>(current,20);
        return this.getBaseMapper().selectShareDetails(page,queryWrapper);
    }

    @Override
    public Map<String,Object> findByLink(String link,String password) {
        if(link != null){
            Map<String,Object> map = new HashMap<>();
            Sharefiles sharefiles = this.query().eq("share_link",link).one();
            if(sharefiles == null){
                throw new WebException(ExceptionCodeMsg.File_NOT_FIND);
            }
            if(isExpire(sharefiles)){
                throw new WebException(ExceptionCodeMsg.INVALID_SHARE);
            }
            QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.select("user_name","avatar");
            queryWrapper1.eq("user_id",sharefiles.getUserId());
            User shareUser = userMapper.selectOne(queryWrapper1);
            map.put("share_user",shareUser);

            if(!sharefiles.getIsPrivate()){
                QueryWrapper<File> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.select("file_id","file_name","file_size","file_type","upload_time","is_folder");
                queryWrapper2.eq("file_id",sharefiles.getFileId());
                List<File> file = fileMapper.selectList(queryWrapper2);
                map.put("file",file);
            }

            if(password != null &&sharefiles.getIsPrivate() && password.equals(sharefiles.getPassword())){
                QueryWrapper<File> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.select("file_id","file_name","file_size","file_type","upload_time","is_folder");
                queryWrapper2.eq("file_id",sharefiles.getFileId());
                List<File> file = fileMapper.selectList(queryWrapper2);
                map.put("file",file);
            }


            sharefiles.setPassword(null);
            map.put("share",sharefiles);
            return map;
        }else {
            throw new WebException(ExceptionCodeMsg.File_NOT_FIND);
        }
    }

    @Override
    public IPage<ShareFileVO> findHotByView() {
        QueryWrapper<ShareFileVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_private",0).orderByDesc("views");

        Page<ShareFileVO> page = new Page<>(1,10);
        return this.getBaseMapper().selectShareDetails(page,queryWrapper);
    }

    @Override
    public Integer updateShareViewsByLink(String link) {
        if(this.update().setSql("views = views + 1").eq("share_link",link).update()){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer updateDownloadCountsByLink(String link) {
        if(this.update().setSql("download_counts = download_counts + 1").eq("share_link",link).update()){
            return 1;
        }else {
            return 0;
        }
    }



    private LocalDateTime generateExpiryTime(int hours) {
        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();
        // 根据传入的小时数生成过期时间
        return currentTime.plusHours(hours);
    }

    private String generateLink(){

        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final SecureRandom random = new SecureRandom();
        int length = 10;

        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    private Boolean isExpire(Sharefiles sharefiles){
        if (sharefiles.getIsExpire()){
            return true;
        }else {
            LocalDateTime now = LocalDateTime.now();
            int result = now.compareTo(sharefiles.getExpireTime());
            if(result > 0){
                this.update().eq("share_link",sharefiles.getShareLink()).set("is_expire",true).update();
                return true;
            }else {
                return false;
            }
        }
    }
}
