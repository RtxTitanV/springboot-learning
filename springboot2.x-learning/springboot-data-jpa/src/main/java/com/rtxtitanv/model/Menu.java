package com.rtxtitanv.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.Menu
 * @description 菜单实体类
 * @date 2020/1/3 20:30
 */
@Setter
@Getter
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_path")
    private String menuPath;
    @Column(name = "menu_hidden")
    private Boolean menuHidden;
    @Column(name = "menu_parent_id")
    private Long menuParentId;
    @Column(name = "menu_icon")
    private String menuIcon;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    //配置中间表，joinColumns：中间表的外键字段关联当前实体类所对应表的主键字段
    //inverseJoinColumns：中间表的外键字段关联对方表的主键字段
    @JoinTable(name = "role_menu",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", menuPath='" + menuPath + '\'' +
                ", menuHidden=" + menuHidden +
                ", menuParentId=" + menuParentId +
                ", menuIcon='" + menuIcon + '\'' +
                '}';
    }
}
