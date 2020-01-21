package com.rtxtitanv.model

import javax.persistence.*

/**
 * @name com.rtxtitanv.model.Menu
 * @description 菜单实体类
 * @author rtxtitanv
 * @date 2020/1/20 21:47
 * @version v1.0.0
 */
@Entity
@Table(name = "menu")
class Menu(@Id
           @GeneratedValue(strategy = GenerationType.IDENTITY)
           @Column(name = "menu_id")
           var menuId: Long? = null,
           @Column(name = "menu_name")
           var menuName: String? = null,
           @Column(name = "menu_path")
           var menuPath: String? = null,
           @Column(name = "menu_hidden")
           var menuHidden: Boolean? = null,
           @Column(name = "menu_parent_id")
           var menuParentId: Long? = null,
           @Column(name = "menu_icon")
           var menuIcon: String? = null,
           @ManyToMany(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
           //配置中间表,joinColumns:中间表的外键字段关联当前实体类所对应表的主键字段
           //inverseJoinColumns:中间表的外键字段关联对方表的主键字段
           @JoinTable(name = "role_menu",
                   joinColumns = [JoinColumn(name = "menu_id")],
                   inverseJoinColumns = [JoinColumn(name = "role_id")])
           var roles: MutableSet<Role> = HashSet()) {

    override fun toString(): String {
        return "Menu{" +
                "menuId=$menuId" +
                ", menuName='$menuName'" +
                ", menuPath='$menuPath'" +
                ", menuHidden=$menuHidden" +
                ", menuParentId=$menuParentId" +
                ", menuIcon='$menuIcon" +
                "}"
    }
}