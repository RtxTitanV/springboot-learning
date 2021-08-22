package com.rtxtitanv.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.UserInfo
 * @description UserInfo
 * @date 2021/8/18 16:45
 */
@Accessors(chain = true)
@Data
public class UserInfo {
    private String gender;
    private String email;
    private String birthday;
}