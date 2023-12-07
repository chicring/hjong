package com.hjong.entity;

public enum ExceptionCodeMsg {


    Email_EXISTS(10001,"邮箱已存在"),
    Email_NOT_EXISTS(10002,"邮箱未注册"),

    INVALID_Register(10003,"注册信息错误"),
    INVALID_CODE(10004,"验证码无效"),
    GET_CODE_Fail(10005,"获取失败,请检查邮箱地址是否正确"),

    Code_NOT_EXISTS(10006,"请先获取验证码"),
    Reset_FAIL(10007,"重置失败"),
    File_Upload_FAIL(10009,"文件上传失败"),
    File_NOT_FIND(10010,"文件或链接不存在"),
    INVALID_File(10011,"不能上传空文件"),
    FAIL_SHARE(10012,"分享失败,请检查分享密码或时间"),
    INVALID_File_SIZE(10013,"文件大小异常"),

    INVALID_SHARE(10014,"文件已过期"),

    FAIL_ACTION(99999,"不能进行此操作"),
    FAIL_USER_ACTION(10015,"操作失败"),
    NOT_LOGIN(401,"用户未登陆"),

    INVALID_PassWord_OR_Email(10008,"账号或密码错误");


    private int code;
    private String msg ;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    ExceptionCodeMsg(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
