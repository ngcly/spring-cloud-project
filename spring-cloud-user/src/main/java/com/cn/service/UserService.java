package com.cn.service;

import com.cn.pojo.Result;
import com.cn.entity.User;
import com.cn.pojo.UserDetail;
import com.cn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author chenning
 * @Classname UserService
 * @Description 用户service层
 * @Date 2019/7/3 14:53
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
        UserDetail userDetail = new UserDetail(user);
        return userDetail;
    }

    public Result getUser(String username){
        return Result.success(userRepository.findByUsername(username));
    }
}
