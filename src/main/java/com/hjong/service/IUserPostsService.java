package com.hjong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjong.entity.UserPosts;
import com.hjong.entity.vo.PostAndUserVO;
import com.hjong.entity.vo.PublishPostVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
public interface IUserPostsService extends IService<UserPosts> {
    Boolean publishUserPost(PublishPostVO vo);
    IPage<PostAndUserVO> findByAny(PublishPostVO vo, Integer current);
    PostAndUserVO findById(Integer Id);
}
