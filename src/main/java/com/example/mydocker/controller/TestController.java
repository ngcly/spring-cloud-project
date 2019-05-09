package com.example.mydocker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenning
 * @Classname TestController
 * @Description 测试
 * @Date 2019/5/9 15:24
 */
@RestController
public class TestController {
    @RequestMapping("/")
    public String home() {
        return "Hello Docker World";
    }
}
