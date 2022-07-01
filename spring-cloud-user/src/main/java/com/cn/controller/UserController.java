package com.cn.controller;

import com.cn.pojo.RestCode;
import com.cn.pojo.Result;
import com.cn.entity.User;
import com.cn.pojo.UserDO;
import com.cn.pojo.UserDetail;
import com.cn.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author chenning
 * @Classname UserController
 * @Description 用户控制层
 * @Date 2019/7/2 20:46
 */
@Tag(name = "UserController", description = "用户相关API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    /**
     * 获取用户信息
     */
    @Operation(summary = "获取用户信息", description = "获取当前用户信息")
    @GetMapping("/info")
    public Result<UserDetail> getUserInfo(Principal principal){
        Authentication authentication = (Authentication) principal;
        UserDetail user = (UserDetail) authentication.getPrincipal();
        return Result.success(user);
    }

    /**
     * 获取当前用户凭证信息
     */
    @RequestMapping("/principal")
    public Principal principal(Principal principal) {
        return principal;
    }

    /**
     * 销毁token
     */
    @RequestMapping(value = "/revoke")
    public Result revoke(@RequestBody ModelMap modelMap) {
        String access_token = modelMap.get("token").toString().substring("Bearer".length()).trim();
        return Result.success();
    }

    @GetMapping("/test")
    public Result test(){
        return Result.success("hello");
    }

    @GetMapping("/info/{username}")
    public Result<UserDO> getUser(@PathVariable("username") String username){
        return userService.getUser(username);
    }
}
