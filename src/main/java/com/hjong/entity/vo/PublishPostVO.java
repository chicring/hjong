package com.hjong.entity.vo;

import lombok.Data;

@Data
public class PublishPostVO {
    private Integer user_id;
    private String post_title;
    private String post_content;
    private String post_time;
    private Integer type_id;
}
