package com.cn.user.controller;

import com.cn.user.pojo.UserDetail;
import com.cn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author ngcly
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public ResponseEntity<UserDetail> getUserInfo(Principal principal){
        Authentication authentication = (Authentication) principal;
        UserDetail user = (UserDetail) authentication.getPrincipal();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/info/{username}")
    public ResponseEntity getUser(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.getUser(username));
    }
}
