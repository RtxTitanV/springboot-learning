package com.rtxtitanv.service.impl;

import com.rtxtitanv.model.CommonResult;
import com.rtxtitanv.model.User;
import com.rtxtitanv.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.service.impl.UserServiceImpl
 * @description UserService实现类
 * @date 2021/8/15 16:46
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public CommonResult<User> updateUser(User user) {
        return CommonResult.ok("更新用户成功", user);
    }
}