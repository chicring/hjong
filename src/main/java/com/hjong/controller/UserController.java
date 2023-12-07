package com.hjong.controller;

import com.hjong.entity.ExceptionCodeMsg;
import com.hjong.entity.RestBean;
import com.hjong.entity.User;
import com.hjong.entity.vo.EmailResetVO;
import com.hjong.service.IUserService;
import com.hjong.util.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chen jianhong
 * @since 2023-11-30
 */
@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Resource
    IUserService iUserService;
    @Resource
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public RestBean<User> login(@RequestParam @Email(message = "邮箱格式不正确") String email, @RequestParam("password") String password, HttpServletResponse response){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        User loginUser = iUserService.loginByEmail(user);

        if (!(loginUser == null)){
            Cookie cookie = new Cookie("token",jwtUtils.createJwt(loginUser));
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            return RestBean.success(loginUser);
        }else {
            return RestBean.success("default");
        }
    }

    @GetMapping("/code")
    public RestBean<Void> VerifyCode(@RequestParam("email") String email, @RequestParam(value = "target",required = false) String target,HttpSession session){

        int code = creatVerifyCode();

        if(target == null){
            session.setAttribute("email",email);
            session.setAttribute("code",code);
        }else {
            session.setAttribute("resetCode",code);
        }
        session.setMaxInactiveInterval(180);


        return RestBean.success(iUserService.registerEmailVerifyCode(email,code));
    }

    @PostMapping ("/register")
    public RestBean<Void> Register(@Valid @RequestBody User newUser, @Positive(message ="验证码不能为空") @RequestParam(value = "code" , required = false) Integer code, HttpSession session){

        if(newUser.getUserName().isEmpty() || newUser.getPassword().isEmpty() || newUser.getEmail().isEmpty()){
            return RestBean.failure(ExceptionCodeMsg.INVALID_Register);
        }
        Map<String,Object> Verifty = new HashMap<>();

        Verifty.put("email", session.getAttribute("email"));
        Verifty.put("code", session.getAttribute("code"));
        Verifty.put("UserCode",code);

        User RegisterUser = iUserService.registerByEmail(newUser, Verifty);
        if(!(RegisterUser == null)){
            return RestBean.success("注册成功");
        }else {
            return RestBean.success("default");
        }
    }

    @PostMapping("/reset")
    public RestBean<Void> resetPassword(@RequestBody EmailResetVO vo, HttpServletRequest request, HttpSession session){

        vo.setCode((Integer) session.getAttribute("resetCode"));
        vo.setUserId((String) request.getAttribute("userId"));

        return RestBean.success(iUserService.resetPasswordByEmail(vo));
    }

    private int creatVerifyCode(){
        int codeLength = 6;
        int minValue = (int) Math.pow(10, codeLength - 1);
        int maxValue = (int) Math.pow(10, codeLength) - 1;

        Random random = new Random();
        return random.nextInt(maxValue - minValue + 1) + minValue;

    }
}
