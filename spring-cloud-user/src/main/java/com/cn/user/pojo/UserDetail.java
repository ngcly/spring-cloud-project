package com.cn.user.pojo;

import com.cn.user.entity.SysMenu;
import com.cn.user.entity.SysRole;
import com.cn.user.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author ngcly
 */
public class UserDetail extends SysUser implements UserDetails {
    public UserDetail(SysUser user) {
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
        for (SysRole role : this.getRoleList()) {
            authority = new SimpleGrantedAuthority(role.getRoleCode());
            authorities.add(authority);
            for(SysMenu permission:role.getMenus()){
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
