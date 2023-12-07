package com.hjong.entity.vo;

import lombok.Data;

@Data
public class UserVO {
    private Integer user_id;
    private String user_name;
    private String email;
    private String introduction;
    private String avatar;
    private Integer role_id;
}
