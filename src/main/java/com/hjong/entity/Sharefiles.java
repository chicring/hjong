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
public class Sharefiles implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件分享ID,主键自增
     */
    @TableId(value = "share_id", type = IdType.AUTO)
    private Integer shareId;

    /**
     * 文件分享链接
     */
    private String shareLink;

    /**
     * 分享密码
     */
    private String password;

    /**
     * 文件浏览次数
     */
    private Integer views;

    /**
     * 文件下载次数
     */
    private Integer downloadCounts;

    /**
     * 是否是私密的
     */
    private Boolean isPrivate;

    /**
     * 是否取消分享
     */
    private Boolean isCancel;

    /**
     * 是否过期
     */
    private Boolean isExpire;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 分享时间 (use TIMESTAMP for automatic timestamping)
     */
    private LocalDateTime shareTime;

    /**
     * 文件ID
     */
    private Integer fileId;
    private Integer userId;

}
