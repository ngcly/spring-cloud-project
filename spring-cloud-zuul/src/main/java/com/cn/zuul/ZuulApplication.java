package com.cn.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author chenning
 * @Classname ZuulApplication
 * @Description 网关启动类  cloud E 以上的版本可以不加 @EnableDiscoverClient 注解
 * @Date 2019/6/14 16:40
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}