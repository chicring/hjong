package com.hjong.entity;

public class WebException extends RuntimeException{
    private int code = 500;
    private String msg = "服务器异常";


    public WebException(ExceptionCodeMsg appExceptionCodeMsg){
        super();
        this.code = appExceptionCodeMsg.getCode();
        this.msg = appExceptionCodeMsg.getMsg();

    }

    public WebException(int code,String msg){
        super();
        this.code = code;
        this.msg = msg;

    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
