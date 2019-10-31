package com.cn.other;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OtherApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtherApplication.class, args);
    }
}
