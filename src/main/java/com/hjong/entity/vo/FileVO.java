package com.hjong.entity.vo;

import lombok.Data;

@Data
public class FileVO {
    private Integer file_id;
    private String file_name;
    private String file_size;
    private String file_type;
    private String upload_time;
    private String modify_time;
    private Boolean is_folder;
    private String file_md5;
}
