package com.hjong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志Id主键自增
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    /**
     * 消息名称
     */
    private String message;

    /**
     * 操作
     */
    private String operation;

    /**
     * 操作日期
     */
    private LocalDateTime operateTime;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 日志级别
     */
    private String logLevel;

    /**
     * 用户ID
     */
    private Integer userId;


}
