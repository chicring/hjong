package com.hjong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjong.entity.ExceptionCodeMsg;
import com.hjong.entity.RestBean;
import com.hjong.entity.vo.PostAndUserVO;
import com.hjong.entity.vo.PublishPostVO;
import com.hjong.service.IUserPostsService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
@RestController
@RequestMapping("/userPosts")
public class UserPostsController {
    @Resource
    IUserPostsService iUserPostsService;

    @PostMapping("/post")
    public RestBean<Void> publicPost(@RequestBody PublishPostVO vo, HttpServletRequest request){
        vo.setUser_id(Integer.parseInt(request.getAttribute("userId").toString()));
        if(iUserPostsService.publishUserPost(vo)){
            return RestBean.success("发布成功");
        }else {
            return RestBean.failure(ExceptionCodeMsg.FAIL_USER_ACTION);
        }
    }

    @GetMapping("/find")
    public RestBean<Map<String,Object>> findByAny(
            @RequestParam(value = "type",required = false) Integer type,
            @RequestParam(value = "content",required = false) String content,
            @RequestParam(value = "current") @NotNull(message = "没有设置查询页") Integer current){

        PublishPostVO vo = null;
        if(type != null || content != null){
            vo = new PublishPostVO();
            vo.setPost_title(content);
            vo.setType_id(type);
        }

        IPage<PostAndUserVO> page = iUserPostsService.findByAny(vo,current);
        Map<String,Object> map = new HashMap<>();
        map.put("count",page.getTotal());
        map.put("size",page.getSize());
        map.put("num",page.getCurrent());
        map.put("list",page.getRecords());

        return RestBean.success(map);
    }

    @GetMapping("/findOne")
    public RestBean<PostAndUserVO> singleFind(@RequestParam("id") Integer Id){
        return RestBean.success(iUserPostsService.findById(Id));
    }
}
