package com.cn.user.service;

import com.cn.user.entity.SysUser;
import com.cn.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ngcly
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public SysUser getUser(String username){
        SysUser user = userRepository.findByUsername(username).orElse(null);
        return user;
    }
}
