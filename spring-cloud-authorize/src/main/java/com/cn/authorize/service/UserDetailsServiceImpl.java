package com.cn.authorize.service;

import com.alibaba.fastjson.JSON;
import com.cn.common.api.UserApiService;
import com.cn.common.pojo.UserDO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

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

        Set<GrantedAuthority> grantedAuthorities = new HashSet();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;

        sysUser.getRoleList().stream().forEach(roleDO -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(roleDO.getRoleCode()));
            roleDO.getMenus().stream().forEach(menuDO -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(menuDO.getPurview()));
            });
        });

        User user = new User(sysUser.getUsername(), sysUser.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }
}
