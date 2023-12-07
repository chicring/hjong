package com.hjong.entity.vo;

import lombok.Data;

@Data
public class ShareCreateVo {
    private String share_link;
    private String password;
    private Boolean is_private;
    private Boolean is_cancel;
    private Boolean is_expire;
    private Integer expire_time;
    private Integer file_id;
    private Integer user_id;
}
