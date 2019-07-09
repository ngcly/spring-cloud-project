package com.cn.common.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author chenning
 * @Classname Result
 * @Description 返回体
 * @Date 2019/7/4 9:54
 */
@Getter
@Setter
public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public Result(){}

    private Result(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
    private Result(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回
     * @return
     */
    public static Result success() {
        return new Result(RestCode.SUCCESS.code,RestCode.SUCCESS.msg);
    }

    /**
     * 一般成功 统一返回
     */
    public static <T> Result success(T data) {
        return new Result(RestCode.SUCCESS.code,RestCode.SUCCESS.msg,data);
    }

    /**
     * 自定义成功消息
     */
    public static <T> Result success(String msg, T data) {
        return new Result(RestCode.SUCCESS.code,msg,data);
    }

    /**
     * 自定义成功返回
     */
    public static <T> Result success(int code,String msg,T data){
        return new Result(code,msg,data);
    }

    /**
     * 一般错误 统一返回
     */
    public static Result failure(RestCode restCode) {
        return new Result(restCode.code,restCode.msg);
    }

    /**
     * 自定义错误信息
     */
    public static Result failure(int code,String msg) {
        return new Result(code,msg);
    }
}
