package com.cn.auth.service;

import com.cn.auth.remote.UserClient;

import com.cn.common.pojo.Result;
import com.cn.common.pojo.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    @Qualifier("spring-cloud-user")
    @Autowired
    UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<UserDO> result = userClient.getUserByUsername(username);
        if(null==result.getData()){
            throw new UsernameNotFoundException("用户：" + username + "不存在！");
        }
        UserDO userDetail = result.getData();
        Collection<GrantedAuthority> authorities = new HashSet<>();
        userDetail.getRoleList().forEach(roleDO -> roleDO.getMenus().forEach(menuDO -> authorities.add(new SimpleGrantedAuthority(menuDO.getPurview()))));
        User user = new User(userDetail.getUsername(),userDetail.getPassword(),authorities);
        return user;
    }
}
