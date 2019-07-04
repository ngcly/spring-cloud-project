package com.cn.user.controller;

import com.cn.common.pojo.Result;
import com.cn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenning
 * @Classname UserController
 * @Description 用户控制层
 * @Date 2019/7/2 20:46
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("info/{username}")
    public Result getUser(@PathVariable("username")String username){
        return userService.getUser(username);
    }
}
