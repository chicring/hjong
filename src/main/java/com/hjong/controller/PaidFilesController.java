package com.hjong.controller;

import com.hjong.entity.ExceptionCodeMsg;
import com.hjong.entity.PaidFiles;
import com.hjong.entity.RestBean;
import com.hjong.entity.WebException;
import com.hjong.service.IPaidFilesService;
import com.hjong.util.FileTools;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/paid")
public class PaidFilesController {

    @Resource
    FileTools fileTools;

    @Resource
    IPaidFilesService iPaidFilesService;

    @Value("${file.upload.path}")
    private String filePath;

    @PostMapping("/create")
    public RestBean<Void> creat(@RequestPart("paidFiles") PaidFiles paidFiles,  @RequestPart("file") MultipartFile file, HttpServletRequest req){
        if(file.isEmpty()){
            throw new WebException(ExceptionCodeMsg.INVALID_File);
        }
        if(file.getSize() > 100*1024*1024){
            throw new WebException(ExceptionCodeMsg.INVALID_File_SIZE);
        }
        PaidFiles saveFile = fileTools.createFile(file,filePath);

        if (saveFile != null){
//            saveFile.setUserId(Integer.parseInt(req.getAttribute("userId").toString()));

            paidFiles.setFilePath(saveFile.getFilePath());
            paidFiles.setFileSize(saveFile.getFileSize());
            paidFiles.setFileName(saveFile.getFileName());
            paidFiles.setFileType(saveFile.getFileType());

            if(iPaidFilesService.createPaidFiles(saveFile)){
                return RestBean.success("发布成功："+ saveFile.getFileName());
            }else {
                return RestBean.failure(ExceptionCodeMsg.File_Upload_FAIL);
            }
        }else {
            return RestBean.failure(ExceptionCodeMsg.File_Upload_FAIL);
        }
    }
}
