package com.hjong.entity.vo;

import lombok.Data;

@Data
public class EmailRegisterVO {
    private int id;
    private String user_name;
    private String password;
    private String real_name;
    private String email;
    private String telephone;
    private String introduction;
    private String address;
    private String sex;
    private String avatar;
    private int is_active;
    private String disk_size;
    private String used_size;
    private int role_id;
    private int code;
    private int userCode;
}
