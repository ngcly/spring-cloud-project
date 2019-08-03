package com.cn.gateway.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author chenning
 * @Classname AuthClient
 * @Description 远程调用授权服务
 * @Date 2019/7/17 9:12
 */
@FeignClient(name = "spring-cloud-user", fallback = UserClient.AuthClientFallback.class)
public interface UserClient {

    @PostMapping("/oauth/token")
    Map<String,?> getToken(@RequestParam("username") String username,@RequestParam("password") String password,
                           @RequestParam("scope") String scope, @RequestParam("grant_type") String grant_type,
                           @RequestParam("client_id") String client_id,@RequestParam("client_secret") String client_secret);

    @GetMapping("/oauth/check_token")
    Map<String,?> checkToken(@RequestParam("token") String token);

    @Service
    class AuthClientFallback implements UserClient {

        @Override
        public Map<String, ?> getToken(String username, String password, String scope, String grant_type, String client_id, String client_secret) {
            return null;
        }

        @Override
        public Map<String, ?> checkToken(String token) {
            return null;
        }
    }
}
