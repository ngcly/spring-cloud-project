package com.cn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author chenning
 * @Classname ResourceServerConfig
 * @Description 静态资源配置
 * @Date 2019/7/8 10:24
 */
@Configuration
@EnableWebSecurity
@Order(99)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.authorizeRequests()
                //.antMatchers("/temp/**").permitAll()
                //.anyRequest()
                //.authenticated()
                //.and()
                .csrf().disable();
    }
}
