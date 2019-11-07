package com.cn.user.controller;

import com.cn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author ngcly
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/principal")
    public ResponseEntity principal(Principal principal) {
        //获取当前用户凭证信息
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/info/{username}")
    public ResponseEntity getUser(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.getUser(username));
    }

}
