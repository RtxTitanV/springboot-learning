package com.rtxtitanv.service;

import com.rtxtitanv.model.CommonResult;
import com.rtxtitanv.model.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.service.UserService
 * @description UserService
 * @date 2021/8/15 16:46
 */
@Validated
public interface UserService {
    /**
     * 更新用户
     *
     * @param user 用户参数
     * @return CommonResult<User>
     */
    CommonResult<User> updateUser(@Valid User user);
}