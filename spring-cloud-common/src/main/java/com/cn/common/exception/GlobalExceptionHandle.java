package com.cn.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandle {

    /**
     * 全局异常处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handlerException(HttpServletRequest request, Exception e){
        return getExceptionResult(e);
    }

    /**
     * 获取异常对应的返回内容
     */
    public static ResponseEntity getExceptionResult(Exception e){
        if (e instanceof GlobalException){
            return ResponseEntity.status(((GlobalException) e).getCode()).body(e.getMessage());
        } else {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
