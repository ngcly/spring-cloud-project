package com.cn.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author chenning
 * @Classname MenuDO
 * @Description 菜单
 * @Date 2019/7/5 15:16
 */
@Getter
@Setter
public class MenuDO implements Serializable {
    private Long id;
    private String name;
    private String resourceType;
    private String url;
    private String purview;
    private String icon;
    private Long parentId;
    private String parentIds;
    private Integer sort;

    @Override
    public String toString() {
        return "MenuDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", url='" + url + '\'' +
                ", purview='" + purview + '\'' +
                ", icon='" + icon + '\'' +
                ", parentId=" + parentId +
                ", parentIds='" + parentIds + '\'' +
                ", sort=" + sort +
                '}';
    }
}
