package com.cn.user.remote;

import com.alibaba.fastjson.JSON;
import com.cn.common.api.UserApiService;
import com.cn.user.entity.SysUser;
import com.cn.user.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ngcly
 */
@Aspect
@Service
public class UserApiServiceImpl implements UserApiService {
    @Autowired
    private UserService userService;

    @Override
    public String findUserByName(String username) {
        SysUser sysUser = userService.getUser(username);
        return JSON.toJSONString(sysUser);
    }
}
