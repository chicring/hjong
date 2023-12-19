package com.hjong.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("PaidFiles")
public class PaidFiles {
    @TableId(type = IdType.AUTO)
    private Integer fileId;
    private String fileName;
    private String fileLink;
    private String filePath;
    private String fileSize;
    private String fileType;
    private String fileDescription;
    private String fileImage;
    private double price;
    private Integer views;
    private Integer downloadCounts;
    private Boolean isCancel;
    private String fileStatus;  //'文件状态：0：正常，1：下架，2：审核'
    private LocalDateTime expireTime;
    private LocalDateTime shareTime;
    private Integer userId;
}
