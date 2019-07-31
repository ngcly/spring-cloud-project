package com.cn.auth.pojo;

import com.cn.common.pojo.MenuDO;
import com.cn.common.pojo.RoleDO;
import com.cn.common.pojo.UserDO;
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
public class UserDetail extends UserDO implements UserDetails {
    public UserDetail(UserDO userDO) {
        if(userDO != null)
        {
            this.setId(userDO.getId());
            this.setUsername(userDO.getUsername());
            this.setRealName(userDO.getRealName());
            this.setPassword(userDO.getPassword());
            this.setGender(userDO.getGender());
            this.setAvatar(userDO.getAvatar());
            this.setState(userDO.getState());
            this.setRoleList(userDO.getRoleList());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        SimpleGrantedAuthority authority;
        for (RoleDO role : this.getRoleList()) {
            authority = new SimpleGrantedAuthority(role.getRoleCode());
            authorities.add(authority);
            for(MenuDO permission:role.getMenus()){
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
