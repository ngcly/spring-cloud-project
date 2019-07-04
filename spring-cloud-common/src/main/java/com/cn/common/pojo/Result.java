package com.cn.common.pojo;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author chenning
 * @Classname Result
 * @Description 返回体
 * @Date 2019/7/4 9:54
 */
@Builder
@Getter
public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    /**
     * 成功返回
     * @return
     */
    public static Result success() {
        Result result = Result.builder().code(RestCode.SUCCESS.code).msg(RestCode.SUCCESS.msg).build();
        return result;
    }

    /**
     * 一般成功 统一返回
     */
    public static <T> Result success(T data) {
        Result result = Result.builder().code(RestCode.SUCCESS.code).msg(RestCode.SUCCESS.msg).data(data).build();
        return result;
    }

    /**
     * 自定义成功消息
     */
    public static <T> Result success(String msg, T data) {
        Result result = Result.builder().code(RestCode.SUCCESS.code).msg(msg).data(data).build();
        return result;
    }

    /**
     * 自定义成功返回
     */
    public static <T> Result success(int code,String msg,T data){
        Result result = Result.builder().code(code).msg(msg).data(data).build();
        return result;
    }

    /**
     * 一般错误 统一返回
     */
    public static Result failure(RestCode restCode) {
        Result result = Result.builder().code(restCode.code).msg(restCode.msg).build();
        return result;
    }

    /**
     * 自定义错误信息
     */
    public static Result failure(int code,String msg) {
        Result result = Result.builder().code(code).msg(msg).build();
        return result;
    }
}
