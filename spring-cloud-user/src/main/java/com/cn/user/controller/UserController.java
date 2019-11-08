package com.cn.user.controller;
import com.cn.common.pojo.UserDetail;
import com.cn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

/**
 * @author ngcly
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     */
    @GetMapping("/info")
    public ResponseEntity getUserInfo(OAuth2Authentication authentication){
        LinkedHashMap map = (LinkedHashMap) authentication.getUserAuthentication().getDetails();
        return ResponseEntity.ok(map.get("principal"));
    }

    @GetMapping("/info/{username}")
    public ResponseEntity getUser(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.getUser(username));
    }

}
