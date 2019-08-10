package com.cn.config;

import com.cn.exception.CustomAccessDeniedHandler;
import com.cn.exception.UnauthorizedEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author chenning
 * @Classname ResourceServerConfig
 * @Description 静态资源配置
 * @Date 2019/6/25 16:42
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                // 认证鉴权错误处理,为了统一异常处理。每个资源服务器都应该加上。
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**","/druid/**","/v2/api-docs/**").permitAll()
                .anyRequest().authenticated();

    }

}
