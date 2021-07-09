package com.rtxtitanv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.User
 * @description User
 * @date 2021/7/3 19:37
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long id;
    private String username;
    private String password;
}