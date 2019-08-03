package com.cn.user.config;

import com.cn.common.pojo.RestCode;
import com.cn.common.pojo.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
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
        if (e instanceof OAuth2Exception) {
            return ResponseEntity.ok(Result.failure(RestCode.UNAUTHZ));
        }
        throw e;
    }

}
