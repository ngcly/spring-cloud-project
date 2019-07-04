package com.cn.auth.service;

import com.cn.auth.remote.UserClient;
import com.cn.common.pojo.Result;
import com.cn.common.pojo.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author chenning
 * @Classname UserDetailsServiceImpl
 * @Description userDetailService实现
 * @Date 2019/6/25 15:36
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<UserDetail> result = userClient.getUserByUsername(username);
        if(null==result.getData()){
            throw new UsernameNotFoundException("用户：" + username + "不存在！");
        }
        Collection<GrantedAuthority> authorities = new HashSet<>();
        User user = new User("","",authorities);
        return user;
    }
}
