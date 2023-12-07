package com.hjong.service.impl;

import com.hjong.entity.Role;
import com.hjong.mapper.RoleMapper;
import com.hjong.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
