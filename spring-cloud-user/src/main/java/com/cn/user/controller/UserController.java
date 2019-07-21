package com.cn.user.controller;

import com.cn.common.pojo.Result;
import com.cn.user.entity.User;
import com.cn.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "UserController", tags = "用户相关API")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "获取用户信息", notes = "根据用户名查询用户信息")
    @GetMapping("info/{username}")
    public Result<User> getUser(@ApiParam("用户名") @PathVariable("username")String username){
        return userService.getUser(username);
    }
}
