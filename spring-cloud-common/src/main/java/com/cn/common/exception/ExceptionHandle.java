package com.cn.common.exception;

import com.cn.common.pojo.RestCode;
import com.cn.common.pojo.Result;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

/**
 * @author chenning
 * @Classname ExceptionHandle
 * @Description 全局异常统一处理
 * @Date 2019/7/4 11:39
 */
@ControllerAdvice
public class ExceptionHandle {

    /**
     * 全局异常处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handlerException(HttpServletRequest request, Exception e){
        e.printStackTrace();
        if (e instanceof AccessDeniedException) {
            return Result.failure(RestCode.UNAUTHZ);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return Result.failure(RestCode.METHOD_ERROR);
        } else if (e instanceof MissingPathVariableException) {
            // 缺少路径参数
            return Result.failure(RestCode.NOT_FOUND);
        } else if (e instanceof MissingServletRequestParameterException) {
            // 缺少必须的请求参数
            return Result.failure(RestCode.PARAM_ERROR);
        } else if (e instanceof HttpMediaTypeNotAcceptableException){
            return Result.failure(RestCode.HEAD_ERROR);
        } else if (e instanceof BindException){
            return Result.failure(400, ((BindException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage());
        } else if (e instanceof GlobalException){
            return Result.failure(((GlobalException) e).getCode(),e.getMessage());
        } else {
            return Result.failure(RestCode.SERVER_ERROR);
        }

    }
}
