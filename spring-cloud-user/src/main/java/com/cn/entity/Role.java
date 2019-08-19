package com.cn.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author chenning
 * @Classname Role
 * @Description 角色实体
 * @Date 2019/7/2 20:45
 */
@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class) //该注解是为了解决当前json序列化的bug 由于many to many会让json序列化产生无限循环 所以该注解能避免restful json产生的死循环
@Table(name="role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id; // 编号
    private String roleName;    //角色名称
    private String roleCode;    //角色标识符 程序中使用
    private String description; //角色描述
    private Boolean available;  //是否可用

    // 用户 - 角色关系定义;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="user_id")})
    private Set<User> userInfoList;// 一个角色对应多个用户

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="role_menu",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="menu_id")})
    @OrderBy("sort asc")
    private Set<Menu> menus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }
}
