package com.hjong.controller;

import com.hjong.entity.*;
import com.hjong.entity.vo.DownloadFileVO;
import com.hjong.service.IFileService;
import com.hjong.service.ISharefilesService;
import com.hjong.util.FileTools;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file.upload.path}")
    private String filePath;
    @Resource
    FileTools fileTools;
    @Resource
    IFileService iFileService;
    @Resource
    ISharefilesService iSharefilesService;

    @PostMapping("/upload")
    public RestBean<Void> uploadSingleFile( @RequestParam("file") MultipartFile file, HttpServletRequest request){

        if(file.isEmpty()){
            throw new WebException(ExceptionCodeMsg.INVALID_File);
        }
        if(file.getSize() > 100*1024*1024){
            throw new WebException(ExceptionCodeMsg.INVALID_File_SIZE);
        }
        File saveFile = fileTools.storeFile(file,filePath);

        if (saveFile != null){

            if (request.getAttribute("userId") != null){
                saveFile.setUserId(Integer.parseInt(request.getAttribute("userId").toString()));
            }
            if(iFileService.addFile(saveFile) > 0){
                return RestBean.success("保存成功："+saveFile.getFileName());
            }else {
                return RestBean.failure(ExceptionCodeMsg.File_Upload_FAIL);
            }
        }else {
            return RestBean.failure(ExceptionCodeMsg.File_Upload_FAIL);
        }
    }

    @PostMapping("/upload-share")
    public RestBean<Sharefiles> uploadAndShare(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "expire_time", required = false) Integer expire_time,
            HttpServletRequest request
    ){
        if(file.isEmpty()){
            throw new WebException(ExceptionCodeMsg.INVALID_File);
        }
        if(file.getSize() > 100*1024*1024){
            throw new WebException(ExceptionCodeMsg.INVALID_File_SIZE);
        }
        //保存文件到目录
        File saveFile = fileTools.storeFile(file,filePath);
        
        Integer user_id = null;

        if (saveFile != null){
            if (request.getAttribute("userId") != null){
                saveFile.setUserId(Integer.parseInt(request.getAttribute("userId").toString()));
                user_id = Integer.parseInt(request.getAttribute("userId").toString());
            }
            //存到数据库并生成分享
            return RestBean.success(iSharefilesService.Share(password,expire_time,iFileService.addFile(saveFile),user_id));
        }else {
            return RestBean.failure(ExceptionCodeMsg.File_Upload_FAIL);
        }

    }
    @GetMapping("/download/{link}/{file_id}")
    public ResponseEntity<org.springframework.core.io.Resource> downloadSingleFile(@PathVariable String link,@PathVariable int file_id,@RequestParam(value = "password",required = false) String password) throws UnsupportedEncodingException {

        DownloadFileVO vo = new DownloadFileVO();
        vo.setLink(link);
        vo.setFileId(file_id);
        vo.setPassword(password);
        DownloadFileVO confirmFile = iFileService.confirmPath(vo);

        org.springframework.core.io.Resource resource = fileTools.loadFileAsResource(confirmFile.getFilePath());
        iSharefilesService.updateDownloadCountsByLink(link);

        String contentType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(confirmFile.getFileName(), StandardCharsets.UTF_8) + "\"")
                .body(resource);
    }

}
