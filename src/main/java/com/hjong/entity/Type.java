package com.hjong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
@Data
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 话题ID
     */
    @TableId(value = "type_id", type = IdType.AUTO)
    private Integer typeId;

    /**
     * 话题名称
     */
    private String typeName;


}
