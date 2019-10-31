package com.cn.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//支持动态刷新配置
@RefreshScope
public class TestController {

    @Value("${user.id}")
    private String name;

    @GetMapping("/hey")
    public String echo() {
        return "hello " + name;
    }
}
