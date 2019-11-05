package com.cn.user.service;

import com.cn.user.entity.SysUser;
import com.cn.user.pojo.UserDetail;
import com.cn.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ngcly
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
        UserDetail userDetail = new UserDetail(user);
        return userDetail;
    }

    public SysUser getUser(String username){
        SysUser user = userRepository.findByUsername(username).orElse(null);
        return user;
    }
}
