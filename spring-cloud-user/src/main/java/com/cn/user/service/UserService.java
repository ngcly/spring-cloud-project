package com.cn.user.service;

import com.cn.user.entity.User;
import com.cn.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenning
 * @Classname UserService
 * @Description 用户service层
 * @Date 2019/7/3 14:53
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }
}
