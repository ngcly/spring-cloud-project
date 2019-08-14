package com.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * @author chenning
 * @Classname OtherApplication
 * @Description 其他服务启动类
 * @Date 2019/8/3 14:48
 */
@SpringBootApplication
@EnableCircuitBreaker
public class OtherApplication {
    public static void main(String[] args) {
        SpringApplication.run(OtherApplication.class, args);
    }
}
