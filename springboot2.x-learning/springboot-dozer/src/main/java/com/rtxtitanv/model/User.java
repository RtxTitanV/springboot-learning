package com.rtxtitanv.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.User
 * @description User
 * @date 2021/8/18 16:45
 */
@Accessors(chain = true)
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String email;
    private Date birthday;
}