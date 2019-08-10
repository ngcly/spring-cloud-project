package com.cn.other.config;

import com.cn.common.exception.CustomAccessDeniedHandler;
import com.cn.common.exception.UnauthorizedEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author chenning
 * @Classname ResourceServerConfig
 * @Description 资源配置
 * @Date 2019/6/25 16:42
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and()
                .authorizeRequests()
                //以下地址不进行认证 直接通过，也可以将以下地址配置到配置文件中
                .antMatchers("/actuator/**","/druid/**","/v2/api-docs/**").permitAll()
                .anyRequest().authenticated();

    }
}
