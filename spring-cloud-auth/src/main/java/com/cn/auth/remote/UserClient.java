package com.cn.auth.remote;

import com.cn.common.pojo.Result;
import com.cn.common.pojo.UserDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author chenning
 * @Classname UserClient
 * @Description 远程调用用户接口
 * @Date 2019/7/2 11:43
 */
@FeignClient(name = "spring-cloud-user", fallback = UserClient.UserClientFallback.class)
public interface UserClient {

    @GetMapping("/user/info/{username}")
    Result<UserDetail> getUserByUsername(@PathVariable("username") String username);

    @Service
    class UserClientFallback implements UserClient{

        @Override
        public Result getUserByUsername(String username) {
            return Result.failure(501,"用户服务熔断降级");
        }
    }
}
