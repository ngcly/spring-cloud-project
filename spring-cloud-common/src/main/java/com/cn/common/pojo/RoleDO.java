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
public class RoleDO implements Serializable {

    private Long id;
    private String roleName;
    private String roleCode;
    private String description;
    private Boolean available;

    private Set<MenuDO> menus;

    @Override
    public String toString() {
        return "RoleDO{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", menus=" + menus +
                '}';
    }
}
