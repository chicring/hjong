package com.hjong.service.impl;

import com.hjong.entity.Type;
import com.hjong.mapper.TypeMapper;
import com.hjong.service.ITypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

}
