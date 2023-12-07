package com.hjong.entity;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

public record RestBean<T> (int code, String message, T data) {

    public static <T> RestBean<T> success(T data){
        return new RestBean<>(200,  "请求成功",data);
    }

    public static <T> RestBean<T> success(){
        return new RestBean<>(200, "请求成功",null);
    }

    public static <T> RestBean<T> success(String message){
        return new RestBean<>(200,message,null);
    }
    public static <T> RestBean<T> failure(ExceptionCodeMsg exceptionCodeMsg){

        return new RestBean<>(exceptionCodeMsg.getCode(), exceptionCodeMsg.getMsg(),null);
    }
    public static <T> RestBean<T> failure(int code, String message){
        return new RestBean<>(code,message,null);
    }

    public static <T> RestBean<T> failure(int code){
        return failure(code, "请求失败");
    }

    public String asJsonString() {
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }
}