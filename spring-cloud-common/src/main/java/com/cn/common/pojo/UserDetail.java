package com.cn.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.management.relation.Role;
import java.util.Set;

/**
 * @author chenning
 * @Classname UserDetail
 * @Description 用户信息
 * @Date 2019/7/4 19:48
 */
@Getter
@Setter
public class UserDetail {

    private Integer id ;
    private String username;
    @JsonIgnore
    private String password;
    private String nickName;
    private Byte gender ;
    private String address;
    private String realName;
    private String personDesc;
    private String signature;
    private String avatar;
    private String phone;
    private String email;
    private Byte state;

    private Set<Role> roleList;
}
