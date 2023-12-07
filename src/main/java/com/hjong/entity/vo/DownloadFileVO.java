package com.hjong.entity.vo;

import lombok.Data;

@Data
public class DownloadFileVO {
    private Integer fileId;
    private String Link;
    private String fileName;
    private String password;
    private String filePath;
}
