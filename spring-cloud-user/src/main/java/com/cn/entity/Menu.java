package com.cn.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author chenning
 * @Classname Menu
 * @Description 菜单资源实体
 * @Date 2019/7/2 20:48
 */
@Getter
@Setter
@Entity
@Table(name="menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    private String name;
    @Column(columnDefinition="enum('menu','button')")
    private String resourceType;
    private String url;
    private String purview;
    private String icon;
    private Long parentId;
    private String parentIds;
    private Integer sort;

    @ManyToMany
    @JoinTable(name="role_menu",joinColumns={@JoinColumn(name="menu_id")},inverseJoinColumns={@JoinColumn(name="role_id")})
    private Set<Role> roles;

}
