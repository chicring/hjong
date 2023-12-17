package com.hjong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjong.entity.ExceptionCodeMsg;
import com.hjong.entity.RestBean;
import com.hjong.entity.Sharefiles;
import com.hjong.entity.vo.ShareCreateVo;
import com.hjong.entity.vo.ShareFileVO;
import com.hjong.service.ISharefilesService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/share")
public class SharefilesController {
    @Resource
    ISharefilesService iSharefilesService;

    @PostMapping("/create")
    public RestBean<Sharefiles> creatShare(@RequestBody ShareCreateVo vo, HttpServletRequest request){
        if(request.getAttribute("userId") != null){
            vo.setUser_id(Integer.parseInt(request.getAttribute("userId").toString()));
        }else {
            vo.setUser_id(null);
        }
        return RestBean.success(iSharefilesService.createShare(vo));
    }

    @GetMapping("/find")
    public RestBean<Map<String,Object>> findByLink(@RequestParam("link") String Link, @RequestParam(value = "password", required = false) String password){
        iSharefilesService.updateShareViewsByLink(Link);
        return RestBean.success(iSharefilesService.findByLink(Link,password));
    }
    @GetMapping("/my")
    public RestBean<IPage<ShareFileVO>> findByUserId(
            @RequestParam("current") Integer current,
            HttpServletRequest request
    ){
        Integer userId =  Integer.parseInt(request.getAttribute("userId").toString());

        return RestBean.success(iSharefilesService.findAllById(userId,current));
    }

    @GetMapping("/detect")
    public RestBean<Void> detectByShareId(@RequestParam("shareId") Integer shareId){
        if (iSharefilesService.deleteShare(shareId)){
            return RestBean.success("删除成功");
        }else {
            return RestBean.failure(ExceptionCodeMsg.FAIL_USER_ACTION);
        }
    }
}
