package com.cn.common.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author ngcly
 * 自定义RocketMQ Binding声明接口
 */
public interface CustomBinding {
    String INPUT1 = "input1";
    String OUTPUT1 = "output1";

    @Input
    SubscribableChannel input1();

    @Output
    MessageChannel output1();
}
