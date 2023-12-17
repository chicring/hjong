package com.hjong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjong.entity.ExceptionCodeMsg;
import com.hjong.entity.UserPosts;
import com.hjong.entity.WebException;
import com.hjong.entity.vo.PostAndUserVO;
import com.hjong.entity.vo.PublishPostVO;
import com.hjong.mapper.UserPostsMapper;
import com.hjong.service.IUserPostsService;
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
public class UserPostsServiceImpl extends ServiceImpl<UserPostsMapper, UserPosts> implements IUserPostsService {

    @Override
    public Boolean publishUserPost(PublishPostVO vo) {

        if(vo.getPost_content() == null){
            throw new WebException(ExceptionCodeMsg.FAIL_USER_ACTION);
        }
        UserPosts posts = new UserPosts();
        posts.setUserId(vo.getUser_id());
        posts.setPostTitle(vo.getPost_title());
        posts.setPostContent(vo.getPost_content());
        posts.setTypeId(vo.getType_id());

        return this.save(posts);
    }

    @Override
    public IPage<PostAndUserVO> findByAny(PublishPostVO vo, Integer current) {

        QueryWrapper<PostAndUserVO> queryWrapper = new QueryWrapper<>();
        Page<PostAndUserVO> page = new Page<>(current,20);

        if(vo == null){
            return getBaseMapper().selectByAny(page,queryWrapper);
        }

        queryWrapper
                .or().like("post_title","%" + vo.getPost_title() + "%")
                .or().like("post_content","%" + vo.getPost_title() + "%")
                .or().eq("type_id",vo.getType_id())
                .or().eq("p.user_id",vo.getUser_id());

        return getBaseMapper().selectByAny(page,queryWrapper);
    }

    @Override
    public PostAndUserVO findById(Integer Id) {
        QueryWrapper<PostAndUserVO> queryWrapper = new QueryWrapper<>();
        Page<PostAndUserVO> page = new Page<>(1,1);
        queryWrapper.eq("post_id",Id);

        return getBaseMapper().selectByAny(page,queryWrapper).getRecords().get(0);
    }

    @Override
    public Boolean delectById(Integer Id) {
        return removeById(Id);
    }
}
