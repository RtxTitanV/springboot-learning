package com.rtxtitanv.model

import javax.persistence.*

/**
 * @name com.rtxtitanv.model.Role
 * @description 角色实体类
 * @author rtxtitanv
 * @date 2020/1/20 21:09
 * @version v1.0.0
 */
@Entity
@Table(name = "role")
class Role(@Id
           @GeneratedValue(strategy = GenerationType.IDENTITY)
           @Column(name = "role_id")
           var roleId: Long? = null,
           @Column(name = "role_name")
           var roleName: String? = null,
           @Column(name = "role_type")
           var roleType: String? = null,
           @ManyToMany(mappedBy = "roles", cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
           var menus: MutableSet<Menu> = HashSet()) {

    override fun toString(): String {
        return "Role{" +
                "roleId=$roleId" +
                ", roleName='$roleName'" +
                ", roleType='$roleType'" +
                "}"
    }
}