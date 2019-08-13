package com.cn.controller;

import com.cn.pojo.Result;
import com.cn.remote.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class LoginController {
    @Autowired
    private UserClient userClient;

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody ModelMap modelMap){
        ResponseEntity<?> entity = userClient.getToken(modelMap.get("username").toString(),modelMap.get("password").toString(),
                "all","password","cloud_client","secret");
        if(HttpStatus.OK == entity.getStatusCode()){
            return ResponseEntity.ok(Result.success(entity.getBody()));
        } else {
            return entity;
        }
    }

    @GetMapping("/user/refreshToken")
    public ResponseEntity refreshToken(@RequestParam("refreshToken")String refreshToken){
        ResponseEntity<?> entity = userClient.refreshToken("refresh_token",refreshToken,"cloud_client","secret");
        if(HttpStatus.OK == entity.getStatusCode()){
            return ResponseEntity.ok(Result.success(entity.getBody()));
        } else {
            return entity;
        }
    }
}
