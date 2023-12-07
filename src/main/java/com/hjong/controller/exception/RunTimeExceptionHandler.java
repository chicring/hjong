package com.hjong.controller.exception;

import com.hjong.entity.FileException;
import com.hjong.entity.RestBean;
import com.hjong.entity.WebException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RunTimeExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public <T> RestBean<T> exceptionHandler(Exception e){
        System.out.println(e);
        //这里先判断拦截到的Exception是不是我们自定义的异常类型
        if(e instanceof WebException){
            WebException webException = (WebException)e;
            return RestBean.failure(webException.getCode(),webException.getMsg());
        }
        //如果拦截的异常不是我们自定义的异常(例如：数据库主键冲突)
        return RestBean.failure(500,"服务器端异常");
    }
}
