package com.cn.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenning
 * @Classname TokenInterceptor
 * @Description Fegin 携带token
 * @Date 2019/8/19 11:08
 */
@Configuration
public class TokenInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            String token = request.getHeader("Authorization");
            template.header("Authorization", new String[] {token});
        }
    }
}
