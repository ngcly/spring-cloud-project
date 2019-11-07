package com.cn.common.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * @author ngcly
 */
@Getter
@Setter
public class UserDO implements Serializable {

    private Integer id ;
    private String username;
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

    private Set<RoleDO> roleList;

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", realName='" + realName + '\'' +
                ", personDesc='" + personDesc + '\'' +
                ", signature='" + signature + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", state=" + state +
                ", roleList=" + roleList +
                '}';
    }
}
