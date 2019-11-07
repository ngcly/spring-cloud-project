package com.cn.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//支持动态刷新配置
@RefreshScope
public class TestController {
    @Value("${my.test}")
    private String name;

    @Autowired
    private MessageChannel output;

    @GetMapping("/hey")
    @SentinelResource("hey")
    public String echo() {
        output.send(MessageBuilder.withPayload(name).build());
        return "hello " + name;
    }
}
