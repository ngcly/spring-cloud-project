package com.cn.user.repository;

import com.cn.user.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ngcly
 */
@Repository
public interface UserRepository extends JpaRepository<SysUser,Integer> {

    Optional<SysUser> findByUsername(String username);
}