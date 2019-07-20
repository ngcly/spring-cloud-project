package com.cn.gateway.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author chenning
 * @Classname AuthClient
 * @Description 远程调用授权服务
 * @Date 2019/7/17 9:12
 */
@FeignClient(name = "spring-cloud-auth", fallback = AuthClient.AuthClientFallback.class)
public interface AuthClient {

    @GetMapping("/oauth/check_token")
    Map<String,?> checkToken(@RequestParam("token") String token);

    @Service
    class AuthClientFallback implements AuthClient{

        @Override
        public Map<String, ?> checkToken(String token) {
            return null;
        }
    }
}
