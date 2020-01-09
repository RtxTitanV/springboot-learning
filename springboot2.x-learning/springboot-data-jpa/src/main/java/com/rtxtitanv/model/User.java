package com.rtxtitanv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author rtxtitanv
 * @version v1.0.0
 * @name com.rtxtitanv.model.User
 * @description 用户实体类
 * @date 2019/12/31 18:49
 */
@Accessors(chain = true) //lombok注解,支持链式编程
@AllArgsConstructor //lombok注解,自动生成全参构造器
@NoArgsConstructor //lombok注解,自动生成无参构造器
@Data //lombok注解,自动生成get,set,toString等方法
@Entity //声明实体类
@Table(name = "user") //建立实体类与表的映射关系
public class User {
    @Id //声明该实例域为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略,IDENTITY:由数据库自行管理
    //建立实例域id与表中字段id的映射关系
    @Column(name = "id")
    private Long id;
    //建立实例域uid和表中字段uid的映射关系,length:指定此字段的长度,只对字符串有效,不写默认为255
    //unique:是否添加唯一约束,不写时默认为false,nullable:表示此字段是否允许为null
    @Column(name = "uid", length = 64, unique = true, nullable = false)
    private String uid;
    //建立实例域userName和表中字段user_name的映射关系
    @Column(name = "user_name", length = 16, unique = true, nullable = false)
    private String userName;
    //建立实例域passWord和表中字段pass_word的映射关系
    @Column(name = "pass_word", length = 16, nullable = false)
    private String passWord;
    //建立实例域nickName和表中字段nick_name的映射关系
    @Column(name = "nick_name", length = 16)
    private String nickName;
    //建立实例域age和表中字段age的映射关系
    @Column(name = "age")
    private Integer age;
    //建立实例域email和表中字段email的映射关系
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    //建立实例域tel和表中字段tel的映射关系
    @Column(name = "tel", unique = true, nullable = false)
    private String tel;
    //建立实例域regTime和表中字段reg_time的映射关系
    @Column(name = "reg_time", nullable = false)
    private String regTime;
    //该实例域并非一个到数据库表的字段的映射,ORM框架将忽略该域
    @Transient
    private String addr;
}
