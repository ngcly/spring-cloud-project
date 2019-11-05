package com.cn.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author ngcly
 */
@Getter
@Setter
@Entity
@Table(name="role")
public class SysRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id; // 编号
    private String roleName;    //角色名称
    private String roleCode;    //角色标识符 程序中使用
    private String description; //角色描述
    private Boolean available;  //是否可用

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="role_menu",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="menu_id")})
    @OrderBy("sort asc")
    private Set<SysMenu> menus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRole role = (SysRole) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }
}
