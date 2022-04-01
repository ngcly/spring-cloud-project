package com.cn.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author chenning
 * @Classname Result
 * @Description 返回体
 * @Date 2019/7/4 9:54
 */
@Schema(name = "返回体")
@Getter
@Setter
public class Result<T> implements Serializable {
    @Schema(name = "状态码")
    private int code;
    @Schema(name = "说明信息")
    private String msg;
    @Schema(name = "内容")
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
