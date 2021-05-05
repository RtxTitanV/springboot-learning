package com.rtxtitanv.model.primary

import javax.persistence.*

/**
 * @name com.rtxtitanv.model.primary.PrimaryUser
 * @description 主数据库用户实体类
 * @author rtxtitanv
 * @date 2020/2/5 18:29
 * @version 1.0.0
 */
@Entity
@Table(name = "user")
data class PrimaryUser(@Id
                       @GeneratedValue(strategy = GenerationType.IDENTITY)
                       @Column(name = "id")
                       var id: Long? = null,
                       @Column(name = "user_name")
                       var userName: String? = null,
                       @Column(name = "pass_word")
                       var passWord: String? = null,
                       @Column(name = "nick_name")
                       var nickName: String? = null,
                       @Column(name = "age")
                       var age: Int? = null,
                       @Column(name = "email")
                       var email: String? = null,
                       @Column(name = "tel")
                       var tel: String? = null)