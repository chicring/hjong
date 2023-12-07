package com.hjong.mapper;

import com.hjong.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
