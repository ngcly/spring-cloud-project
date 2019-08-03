package com.cn.gateway.controller;

import com.cn.common.pojo.RestCode;
import com.cn.common.pojo.Result;
import com.cn.gateway.remote.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    public Result login(@RequestBody ModelMap modelMap){
        Map<String,?> map = userClient.getToken(modelMap.get("username").toString(),modelMap.get("password").toString(),
                "all","password","cloud_client","secret");
        if(map!=null){
            if(map.get("access_token")!=null){
                Map m = new HashMap();
                m.put("token",map.get("token_type")+" "+map.get("access_token"));
                return Result.success(m);
            }else {
                return Result.failure(RestCode.USER_ERR);
            }
        }else {
            return Result.failure(RestCode.SERVICE_UNAVAILABLE);
        }
    }
}
