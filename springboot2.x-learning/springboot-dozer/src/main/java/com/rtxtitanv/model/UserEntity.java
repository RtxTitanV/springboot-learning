package com.rtxtitanv.model;

import com.github.dozermapper.core.Mapping;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.UserEntity
 * @description UserEntity
 * @date 2021/8/18 16:45
 */
@Accessors(chain = true)
@Data
public class UserEntity {
    @Mapping(value = "userId")
    private Long id;
    @Mapping(value = "userName")
    private String name;
    @Mapping(value = "userAge")
    private Integer age;
    private String gender;
    private String email;
    private String birthday;
}