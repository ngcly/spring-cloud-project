package com.cn.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author chenning
 * @Classname ResourceServerConfig
 * @Description 资源服务
 * @Date 2019/6/25 16:42
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                //.authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**","/druid/**").permitAll()
                .anyRequest().authenticated();

    }
}
