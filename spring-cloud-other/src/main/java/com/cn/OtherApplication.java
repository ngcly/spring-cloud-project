package com.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chenning
 * @Classname OtherApplication
 * @Description 其他服务启动类
 * @Date 2019/8/3 14:48
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableOAuth2Sso
public class OtherApplication {
    public static void main(String[] args) {
        SpringApplication.run(OtherApplication.class, args);
    }
}