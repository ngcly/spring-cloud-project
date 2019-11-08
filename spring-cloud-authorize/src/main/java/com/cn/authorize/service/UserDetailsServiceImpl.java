package com.cn.authorize.service;

import com.alibaba.fastjson.JSON;
import com.cn.common.api.UserApiService;
import com.cn.common.pojo.UserDO;
import com.cn.common.pojo.UserDetail;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author ngcly
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Reference
    private UserApiService userApiService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String result = userApiService.findUserByName(username);
        UserDO sysUser = JSON.parseObject(result,UserDO.class);
        if(sysUser==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        UserDetail userDetail = new UserDetail(sysUser);
        return userDetail;
    }
}
