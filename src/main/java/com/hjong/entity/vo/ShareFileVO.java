package com.hjong.entity.vo;

import lombok.Data;

@Data
public class ShareFileVO {
    private Integer share_id;
    private String share_link;
    private String password;
    private Integer views;
    private Integer download_counts;
    private Boolean is_private;
    private Boolean is_expire;
    private String expire_time;
    private String share_time;
    private Integer file_id;
    private UserVO user;
    private FileVO file;
}
