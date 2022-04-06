package com.cn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenning
 * @Classname AuthClient
 * @Description 远程调用授权服务
 * @Date 2019/7/17 9:12
 */
@FeignClient(name = "spring-cloud-user", fallback = UserClient.AuthClientFallback.class)
public interface UserClient {

    @PostMapping("/oauth/token")
    ResponseEntity<?> getToken(@RequestParam("username") String username, @RequestParam("password") String password,
                            @RequestParam("scope") String scope, @RequestParam("grant_type") String grant_type,
                            @RequestParam("client_id") String client_id, @RequestParam("client_secret") String client_secret);

    @GetMapping("/oauth/token")
    ResponseEntity<?> refreshToken(@RequestParam("grant_type") String grantType,@RequestParam("refresh_token") String refreshToken,
                                   @RequestParam("client_id") String clientId, @RequestParam("client_secret") String clientSecret);

    @GetMapping("/oauth/check_token")
    ResponseEntity<?> checkToken(@RequestParam("token") String token);

    @Service
    class AuthClientFallback implements UserClient {

        @Override
        public ResponseEntity<?> getToken(String username, String password, String scope, String grant_type, String client_id, String client_secret) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }

        @Override
        public ResponseEntity<?> refreshToken(String grantType, String refreshToken, String clientId, String clientSecret) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }

        @Override
        public ResponseEntity<?> checkToken(String token) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }
}
