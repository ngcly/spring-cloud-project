package com.cn.client;

import com.cn.pojo.RestCode;
import com.cn.pojo.Result;
import com.cn.pojo.UserDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用授权服务
 * @author ngcly
 * @since 2019/7/17 9:12
 */
@FeignClient(name = "spring-cloud-user", fallback = UserClient.AuthClientFallback.class)
public interface UserClient {

    @GetMapping("/user/info/{username}")
    Result<UserDO> getUserInfoByName(@PathVariable("username")String username);

    @Service
    class AuthClientFallback implements UserClient {

        @Override
        public Result<UserDO> getUserInfoByName(String username) {
            return Result.failure(RestCode.SERVICE_UNAVAILABLE);
        }
    }
}
