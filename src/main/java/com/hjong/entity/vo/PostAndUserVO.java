package com.hjong.entity.vo;

import lombok.Data;

@Data
public class PostAndUserVO {
    private Integer post_id;
    private Integer user_id;
    private String post_title;
    private String post_content;
    private String post_time;
    private String likes_count;
    private String comments_count;
    private Integer type_id;
    private UserVO user;
}
