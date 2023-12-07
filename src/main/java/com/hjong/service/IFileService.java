package com.hjong.service;

import com.hjong.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjong.entity.vo.DownloadFileVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
public interface IFileService extends IService<File> {

    File findById(int fileId);
    int addFile(File file);
    int deleteFile(File file);
    DownloadFileVO confirmPath(DownloadFileVO vo);
}
