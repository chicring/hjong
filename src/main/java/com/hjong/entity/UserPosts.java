package com.hjong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user_posts")
public class UserPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动态ID，主键自增
     */
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    /**
     * 用户ID
     */
    private Integer userId;

    private String postTitle;
    /**
     * 动态内容
     */
    private String postContent;

    /**
     * 发表时间
     */
    private LocalDateTime postTime;

    /**
     * 点赞数量
     */
    private Integer likesCount;

    /**
     * 评论数量
     */
    private Integer commentsCount;

    /**
     * 动态话题
     */
    private Integer typeId;

}
