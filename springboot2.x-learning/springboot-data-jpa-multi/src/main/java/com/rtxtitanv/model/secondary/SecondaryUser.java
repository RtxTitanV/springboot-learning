package com.rtxtitanv.model.secondary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.secondary.SecondaryUser
 * @description 从数据库用户实体类
 * @date 2020/1/7 19:32
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class SecondaryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "pass_word")
    private String passWord;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "email")
    private String email;
    @Column(name = "tel")
    private String tel;
}