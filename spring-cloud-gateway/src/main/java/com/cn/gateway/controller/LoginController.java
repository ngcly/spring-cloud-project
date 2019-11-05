package com.cn.gateway.controller;

import com.cn.gateway.remote.UserClient;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ngcly
 */
@RestController
public class LoginController {
    @Resource
    private UserClient userClient;

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody ModelMap modelMap){
        return userClient.getToken(modelMap.get("username").toString(),modelMap.get("password").toString(),
                "all","password","cloud_client","secret");
    }

    @GetMapping("/user/refreshToken")
    public ResponseEntity refreshToken(@RequestParam("refreshToken")String refreshToken){
        return userClient.refreshToken("refresh_token",refreshToken,"cloud_client","secret");
    }

}
