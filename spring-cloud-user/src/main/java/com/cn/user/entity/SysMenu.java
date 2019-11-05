package com.cn.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author ngcly
 */
@Getter
@Setter
@Entity
@Table(name="menu")
public class SysMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
