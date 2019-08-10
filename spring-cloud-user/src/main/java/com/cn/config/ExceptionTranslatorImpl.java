package com.cn.config;

import com.cn.exception.GlobalExceptionHandle;
import com.cn.pojo.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @author chenning
 * @Classname ExceptionTranslatorImpl
 * @Description 异常翻译
 * @Date 2019/7/8 14:45
 */
public class ExceptionTranslatorImpl implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Result result = new GlobalExceptionHandle().handlerException(request,e);
        return ResponseEntity.ok(result);
    }

}
