package com.hjong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件ID，主键自增
     */
    @TableId(value = "file_id", type = IdType.AUTO)
    private Integer fileId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 存储路径
     */
    private String filePath;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 上传时间 (use TIMESTAMP for automatic timestamping)
     */
    private LocalDateTime uploadTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 是否被删除
     */
    private Boolean isDelete;

    /**
     * 是否是文件夹
     */
    private Boolean isFolder;

    /**
     * 文件md5
     */
    private String fileMd5;

    /**
     * 父级目录ID
     */
    private Integer parentId;

    /**
     * 用户id
     */
    private Integer userId;

}
