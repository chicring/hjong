package com.hjong.controller;

import com.hjong.entity.RestBean;
import com.hjong.entity.Sharefiles;
import com.hjong.entity.vo.ShareCreateVo;
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
    public RestBean<Map<String,Object>> findByLink(@RequestParam("link") String Link, @RequestParam(value = "password", required = false) String password, HttpServletRequest request){
        iSharefilesService.updateShareViewsByLink(Link);
        return RestBean.success(iSharefilesService.findByLink(Link,password));
    }
    @GetMapping("/my")
    public RestBean<Void> findByUserId(){
        return null;
    }


}
