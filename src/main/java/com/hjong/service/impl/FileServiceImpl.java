package com.hjong.service.impl;

import com.hjong.entity.ExceptionCodeMsg;
import com.hjong.entity.File;
import com.hjong.entity.Sharefiles;
import com.hjong.entity.WebException;
import com.hjong.entity.vo.DownloadFileVO;
import com.hjong.mapper.FileMapper;
import com.hjong.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjong.service.ISharefilesService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    @Resource
    ISharefilesService iSharefilesService;

    @Override
    public File findById(int fileId) {

        File file = this.query().eq("file_id",fileId).one();
        if(file != null){
            return file;
        }else {
            throw new WebException(ExceptionCodeMsg.File_NOT_FIND);
        }
    }

    @Override
    public int addFile(File file) {

        if(this.save(file)){
            return file.getFileId();
        }else {
            throw new WebException(ExceptionCodeMsg.File_Upload_FAIL);
        }
    }

    @Override
    public int deleteFile(File file) {

        if(this.removeById(file)){
            return 1;
        }else {
            throw new WebException(ExceptionCodeMsg.File_NOT_FIND);
        }
    }

    @Override
    public DownloadFileVO confirmPath(DownloadFileVO vo) {
        Sharefiles share = iSharefilesService.query().eq("share_link",vo.getLink()).one();
        if(share == null){
            throw new WebException(ExceptionCodeMsg.File_NOT_FIND);
        }
        if(isExpire(share)){
            throw new WebException(ExceptionCodeMsg.INVALID_SHARE);
        }
        if(share.getIsPrivate()){
            if(share.getPassword().equals(vo.getPassword())){
                File file = this.query().eq("file_id",vo.getFileId()).one();
                vo.setFilePath(file.getFilePath());
                vo.setFileName(file.getFileName());
                return vo;
            }else {
                throw new WebException(ExceptionCodeMsg.FAIL_ACTION);
            }
        }else {
            File file = this.query().eq("file_id",vo.getFileId()).one();
            vo.setFilePath(file.getFilePath());
            vo.setFileName(file.getFileName());
            return vo;
        }
    }

    private Boolean isExpire(Sharefiles sharefiles){
        if (sharefiles.getIsExpire()){
            return true;
        }else {
            LocalDateTime now = LocalDateTime.now();
            int result = now.compareTo(sharefiles.getExpireTime());
            if(result > 0){
                iSharefilesService.update().eq("share_link",sharefiles.getShareLink()).set("is_expire",true).update();
                return true;
            }else {
                return false;
            }
        }
    }
}
