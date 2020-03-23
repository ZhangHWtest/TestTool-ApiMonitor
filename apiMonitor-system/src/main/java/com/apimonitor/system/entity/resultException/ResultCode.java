package com.apimonitor.system.entity.resultException;

/**
 * 公共的返回码
 */
public enum ResultCode {

    SUCCESS(200,"操作成功！"),
    //---系统错误返回码-----
    FAIL(10001,"操作失败！"),
    UNAUTHENTICATED(402,"您还未登录！"),
    SERVER_ERROR(99999,"抱歉，系统繁忙，请稍后重试！"),

    //---用户操作返回码  2xxxx----
    MOBILEORPASSWORDERROR(20001,"用户名或密码错误！"),
    UNAUTHORISE(20002,"权限不足"),
    TOKENEXPIRE(20003,"token失效请重新登录！"),
    DISABLDE(20004,"账号已停用，请联系管理员！");
    //---企业操作返回码  3xxxx----
    //---权限操作返回码----
    //---其他操作返回码----

    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }


    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

}
