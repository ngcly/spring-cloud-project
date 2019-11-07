package com.cn.authorize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 开启资源服务
 * @author ngcly
 */
@RestController
@RequestMapping("/authorize")
public class AuthorizeController {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping(value = "/principal")
    public ResponseEntity principal(Principal principal) {
        //获取用户凭证信息
        return ResponseEntity.ok(principal);
    }

    @DeleteMapping(value = "/revoke")
    public ResponseEntity revoke(String access_token) {
        if (consumerTokenServices.revokeToken(access_token)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }
}
