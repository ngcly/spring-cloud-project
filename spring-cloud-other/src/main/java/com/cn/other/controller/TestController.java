package com.cn.other.controller;

import com.cn.api.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Reference
    private HelloService helloService;

    @GetMapping("/test")
    public String test() {
        return helloService.hello("welcome to here");
    }
}
