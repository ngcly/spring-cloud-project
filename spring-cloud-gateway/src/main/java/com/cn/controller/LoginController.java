package com.cn.controller;

import com.cn.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenning
 * @Classname Login
 * @Description 登录
 * @Date 2019/7/29 9:34
 */
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserClient userClient;

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody ModelMap modelMap){
        ResponseEntity<String> entity = userClient.getToken(modelMap.get("username").toString(),modelMap.get("password").toString());
        return entity;
    }

    @GetMapping("/user/refreshToken")
    public ResponseEntity refreshToken(@RequestParam("refreshToken")String refreshToken){
        ResponseEntity<?> entity = userClient.refreshToken("refresh_token",refreshToken,"cloud_client","secret");
        return entity;
    }
}
