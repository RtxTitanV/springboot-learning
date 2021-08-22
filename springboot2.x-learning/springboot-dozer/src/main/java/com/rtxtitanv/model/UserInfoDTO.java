package com.rtxtitanv.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.UserInfoDTO
 * @description UserInfoDTO
 * @date 2021/8/18 16:45
 */
@Accessors(chain = true)
@Data
public class UserInfoDTO {
    private Long userId;
    private String userName;
    private Integer userAge;
    private UserInfo userInfo;
}