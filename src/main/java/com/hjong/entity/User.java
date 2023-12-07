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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID，主键自增
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String telephone;

    /**
     * 个人简介
     */
    private String introduction;

    /**
     * 住址
     */
    private String address;

    /**
     * 性别
     */
    private String sex;

    /**
     * 用户头像存放路径
     */
    private String avatar;

    /**
     * 状态，0：未激活；1：正常；2：禁用
     */
    private Integer isActive;

    /**
     * 网盘大小
     */
    private String diskSize;

    /**
     * 已用大小
     */
    private String usedSize;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 角色
     */
    private Integer roleId;


}
