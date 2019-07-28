package com.cn.auth.controller;

import com.cn.common.pojo.RestCode;
import com.cn.common.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author chenning
 * @Classname AuthController
 * @Description 授权控制层
 * @Date 2019/7/8 10:30
 */
@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    /**
     * 获取当前用户凭证信息
     */
    @RequestMapping("/principal")
    public Principal principal(Principal principal) {
        return principal;
    }

    @GetMapping("/info")
    public Result<User> getUserInfo(Principal principal){
        Authentication authentication = (Authentication) principal;
        User user = (User) authentication.getPrincipal();
        return Result.success(user);
    }

    /**
     * 销毁token
     * @param access_token
     * @return
     */
    @RequestMapping(value = "/revoke")
    public Result revoke(String access_token) {
        return consumerTokenServices.revokeToken(access_token)?Result.success():Result.failure(RestCode.SERVER_ERROR);
    }
}
