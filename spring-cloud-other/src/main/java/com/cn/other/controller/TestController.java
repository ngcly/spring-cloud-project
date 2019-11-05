package com.cn.other.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cn.common.api.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {
    @Reference
    private HelloService helloService;

    @Value("${hey.mm}")
    private String mm;

    @GetMapping("/test")
    @SentinelResource("test")
    public String test() {
        return helloService.hello("welcome to here");
    }

    @GetMapping("/hey")
    @SentinelResource("hey")
    public String hey(){
        return "mm: "+mm;
    }
}
