package com.cn.user.controller;

import com.cn.user.entity.User;
import com.cn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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
    public ModelMap getUser(@PathVariable("username")String username){
        ModelMap modelMap = new ModelMap();
        modelMap.put("data",userService.getUser(username));
        return modelMap;
    }
}
