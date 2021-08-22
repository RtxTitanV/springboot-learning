package com.rtxtitanv.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.UserVO
 * @description UserVO
 * @date 2021/8/18 16:45
 */
@Accessors(chain = true)
@Data
public class UserVO {
    private Long userId;
    private String userName;
    private Integer userAge;
    private String gender;
    private String email;
    private Date birthday;
}