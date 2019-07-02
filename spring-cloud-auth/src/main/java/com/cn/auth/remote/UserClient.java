package com.cn.auth.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * @author chenning
 * @Classname UserClient
 * @Description 远程调用用户接口
 * @Date 2019/7/2 11:43
 */
@FeignClient(name = "spring-cloud-user", fallback = UserClient.UserClientFallback.class)
public interface UserClient {

    @Service
    class UserClientFallback implements UserClient{

    }
}
