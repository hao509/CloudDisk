package com.example.clouddisk.enums;

/***
 * @author wjl
 * @date 2022/10/6
 **/
public enum BusinessCodeEnum {

    NOT_LOGIN(10000,"请先登录"),

    DATA_EXIST(10001,"数据已存在"),
    DATA_NOT_EXIST(10002,"数据不存在"),
    PASSWORD_INVALID(10003,"密码错误"),
    PASSWORD_INVALID_SAME(10004,"新旧密码一致"),
    INVALID_LOGIN(10005,"登录信息已过期，请重新登录"),
    INVALID_COOKIE(10006,"cookie无效"),
    ACCOUNT_INVALID(10007,"账户不存在"),

    OPERATION_FAILED(20000,"操作失败,请联系管理员"),
    VALID_EXCEPTION(20001,"参数格式校验"),
    VALID_PERMISSION(30001,"没有权限"),
    VALID_EXPIRED(00000,"授权过期");



    private Integer code;
    private String msg;

    BusinessCodeEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
