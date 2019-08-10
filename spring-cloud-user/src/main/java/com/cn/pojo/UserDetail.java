package com.cn.pojo;

import com.cn.entity.Menu;
import com.cn.entity.Role;
import com.cn.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author chenning
 * @Classname UserDetail
 * @Description security 自定义用户
 * @Date 2019/7/31 11:55
 */
public class UserDetail extends User implements UserDetails {
    public UserDetail(User user) {
        if(user != null)
        {
            this.setId(user.getId());
            this.setUsername(user.getUsername());
            this.setRealName(user.getRealName());
            this.setPassword(user.getPassword());
            this.setGender(user.getGender());
            this.setAvatar(user.getAvatar());
            this.setState(user.getState());
            this.setRoleList(user.getRoleList());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        SimpleGrantedAuthority authority;
        for (Role role : this.getRoleList()) {
            authority = new SimpleGrantedAuthority(role.getRoleCode());
            authorities.add(authority);
            for(Menu permission:role.getMenus()){
                authority = new SimpleGrantedAuthority(permission.getPurview());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
