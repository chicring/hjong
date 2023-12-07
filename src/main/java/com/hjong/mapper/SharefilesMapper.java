package com.hjong.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.hjong.entity.Sharefiles;
import com.hjong.entity.vo.ShareFileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
@Mapper
public interface SharefilesMapper extends BaseMapper<Sharefiles> {
    IPage<ShareFileVO> selectShareDetails(IPage<ShareFileVO> page, @Param(Constants.WRAPPER) Wrapper<ShareFileVO> queryWrapper);
}
