package com.rtxtitanv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.User
 * @description 用户实体类
 * @date 2021/5/26 18:34
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "user")
public class User {
    @Id
    private ObjectId id;
    @Field(name = "username")
    private String username;
    @Field(name = "password")
    private String password;
    @Field(name = "realname")
    private String realname;
    @Field(name = "gender")
    private String gender;
    @Field(name = "age")
    private Integer age;
    @Field(name = "email")
    private String email;
    @Field(name = "user_point")
    private Integer userPoint;
    @Field(value = "user_level")
    private Byte userLevel;
    @Field(name = "birthday")
    private LocalDateTime birthday;
}