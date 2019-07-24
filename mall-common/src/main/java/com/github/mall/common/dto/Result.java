package com.github.mall.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述:
 * 返回实体
 *
 * @author HPC
 * @create 2019-01-31
 */
@Data
public class Result<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T> Result<T> success() {
        return new Result<>(Code.SUCCESS.getKey(), Code.SUCCESS.getValue(), null);
    }

    public static <T> Result<T> success(T obj) {
        return new Result<>(Code.SUCCESS.getKey(), Code.SUCCESS.getValue(), obj);
    }

    public static <T> Result<T> success(Code code) {
        return new Result<>(code.getKey(), code.getValue(), null);
    }

    public static <T> Result<T> success(Code code, T obj) {
        return new Result<>(code.getKey(), code.getValue(), obj);
    }

    public static <T> Result<T> fail() {
        return new Result<>(Code.FAIL.getKey(), Code.FAIL.getValue(), null);
    }

    public static <T> Result<T> fail(T obj) {
        return new Result<>(Code.FAIL.getKey(), Code.FAIL.getValue(), obj);
    }

    public static <T> Result<T> fail(Code code) {
        return new Result<>(code.getKey(), code.getValue(), null);
    }

    public static <T> Result<T> fail(Code code, T obj) {
        return new Result<>(code.getKey(), code.getValue(), obj);
    }

}
