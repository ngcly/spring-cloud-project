package com.cn.user.config;

import com.cn.common.pojo.RestCode;
import com.cn.common.pojo.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;


/**
 * @author chenning
 * @Classname ExceptionTranslatorImpl
 * @Description 异常翻译
 * @Date 2019/7/8 14:45
 */
public class ExceptionTranslatorImpl implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity translate(Exception e) throws Exception {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        Result responseData = GlobalExceptionHandler.resolveOauthException(e,request.getRequestURI());
        return ResponseEntity.ok(Result.failure(RestCode.USER_ERR));
    }

}
