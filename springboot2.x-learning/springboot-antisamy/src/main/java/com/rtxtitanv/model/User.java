package com.rtxtitanv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.User
 * @description 用户实体类
 * @date 2021/8/23 14:54
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long id;
    private String username;
    private String password;
}