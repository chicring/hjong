package com.hjong.entity.vo;

import lombok.Data;

@Data
public class EmailResetVO {

    private String userId;
    private String email;
    private Integer code;
    private Integer userCode;
    private String password;
}
