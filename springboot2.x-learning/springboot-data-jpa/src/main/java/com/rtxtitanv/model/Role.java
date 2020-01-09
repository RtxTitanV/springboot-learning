package com.rtxtitanv.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rtxtitanv
 * @version v1.0.0
 * @name com.rtxtitanv.model.Role
 * @description 角色实体类
 * @date 2020/1/3 20:29
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_type")
    private String roleType;
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Menu> menus = new HashSet<>();

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleType='" + roleType + '\'' +
                '}';
    }
}
