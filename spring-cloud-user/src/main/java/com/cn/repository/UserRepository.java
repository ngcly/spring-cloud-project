package com.cn.repository;

import com.cn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author chenning
 * @Classname UserRepository
 * @Description 用户sql查询层
 * @Date 2019/7/3 14:15
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);
}
