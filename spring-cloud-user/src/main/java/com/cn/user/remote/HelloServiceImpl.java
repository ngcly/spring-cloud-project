package com.cn.user.remote;

import com.cn.common.api.HelloService;
import org.apache.dubbo.config.annotation.Service;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @author ngcly
 */
@Aspect
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hello " + name;
    }

    @StreamListener("input")
    public void receiveInput(String receiveMsg) {
        System.out.println("input receive: " + receiveMsg);
    }
}
