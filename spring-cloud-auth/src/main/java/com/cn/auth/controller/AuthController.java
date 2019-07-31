package com.cn.auth.controller;

import com.cn.auth.pojo.UserDetail;
import com.cn.common.pojo.RestCode;
import com.cn.common.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    public Result<UserDetail> getUserInfo(Principal principal){
        Authentication authentication = (Authentication) principal;
        UserDetail user = (UserDetail) authentication.getPrincipal();
        return Result.success(user);
    }

    /**
     * 销毁token
     */
    @RequestMapping(value = "/revoke")
    public Result revoke(@RequestBody ModelMap modelMap) {
        String access_token = modelMap.get("token").toString().substring("Bearer".length()).trim();
        return consumerTokenServices.revokeToken(access_token)?Result.success():Result.failure(RestCode.SERVER_ERROR);
    }
}
