package com.rtxtitanv.model

import javax.persistence.*

/**
 * @name com.rtxtitanv.model.User
 * @description 用户实体类
 * @author rtxtitanv
 * @date 2020/1/17 16:40
 * @version v1.0.0
 */
@Entity //声明实体类
@Table(name = "user") //建立实体类与表的映射关系
data class User(@Id  //声明该实例域为主键
                //主键生成策略，IDENTITY：由数据库自行管理
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                //建立实例域id与表中字段id的映射关系
                @Column(name = "id")
                //Kotlin变量默认Non-Null，?声明变量为Nullable变量，即允许为null，这里表示类中该属性允许为null
                var id: Long? = null,
                //建立实例域uid和表中字段uid的映射关系，length：指定此字段的长度，只对字符串有效，不写默认为255
                //unique：是否添加唯一约束，不写时默认为false，nullable：表示此字段是否允许为null
                @Column(name = "uid", length = 64, unique = true, nullable = false)
                var uid: String? = null,
                //建立实例域userName和表中字段user_name的映射关系
                @Column(name = "user_name", length = 16, unique = true, nullable = false)
                var userName: String? = null,
                //建立实例域passWord和表中字段pass_word的映射关系
                @Column(name = "pass_word", length = 16, nullable = false)
                var passWord: String? = null,
                //建立实例域nickName和表中字段nick_name的映射关系
                @Column(name = "nick_name", length = 16)
                var nickName: String? = null,
                //建立实例域age和表中字段age的映射关系
                @Column(name = "age")
                var age: Int? = null,
                //建立实例域email和表中字段email的映射关系
                @Column(name = "email", unique = true, nullable = false)
                var email: String? = null,
                //建立实例域tel和表中字段tel的映射关系
                @Column(name = "tel", unique = true, nullable = false)
                var tel: String? = null,
                //建立实例域regTime和表中字段reg_time的映射关系
                @Column(name = "reg_time", nullable = false)
                var regTime: String? = null,
                //该实例域并非一个到数据库表的字段的映射，ORM框架将忽略该域
                @Transient
                var addr: String? = null)