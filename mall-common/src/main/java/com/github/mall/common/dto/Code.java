package com.github.mall.common.dto;

/**
 * 描述:
 * 响应码
 *
 * @author HPC
 * @create 2019-01-31
 */
public enum Code {
    /**
     * 0开头的是请求成功
     * 1开头的请求失败
     * 2开头的参数错误
     */
    SUCCESS("0000", "请求成功"),

    UPDATE_PW_SUCCESS("0001", "修改密码成功"),

    FAIL("1000", "请求失败"),

    TOKEN_NOT("1001", "请求头没有access_token"),

    TOKEN_PASSED("1002", "access_token失效"),

    LOGIN_PWD_OR_ACONUNT_ERR("1003", "登陆错误，密码或者账号不存在"),

    NO_RIGHT("1004", "没有操作权限"),

    BLACK_IP("1005", "你以被禁止访问"),

    RECOMMIT("1006", "请求过于频繁"),

    PARAM("2000", "请求参数错误"),

    ;

    private String key;
    private String value;

    Code(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
