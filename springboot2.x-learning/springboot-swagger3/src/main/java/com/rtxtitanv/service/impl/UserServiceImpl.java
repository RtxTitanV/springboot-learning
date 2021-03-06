package com.rtxtitanv.service.impl;

import com.rtxtitanv.exception.InvalidParameterException;
import com.rtxtitanv.exception.NotFoundException;
import com.rtxtitanv.model.CommonResult;
import com.rtxtitanv.model.User;
import com.rtxtitanv.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.service.impl.UserServiceImpl
 * @description UserService实现类
 * @date 2021/6/7 12:49
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 创建线程安全的Map，模拟用户信息的存储
     */
    private static final Map<Long, User> USER_MAP = Collections.synchronizedMap(new HashMap<>());

    @Override
    public CommonResult<User> saveUser(User user) {
        if (user.getId() <= 0) {
            throw new InvalidParameterException("无效参数");
        }
        USER_MAP.put(user.getId(), user);
        return CommonResult.success("保存用户成功", user);
    }

    @Override
    public CommonResult<List<User>> findUserAll() {
        return CommonResult.success("查询所有用户成功", new ArrayList<>(USER_MAP.values()));
    }

    @Override
    public CommonResult<User> findUserById(Long id) {
        if (id <= 0) {
            throw new InvalidParameterException("无效参数");
        }
        return CommonResult.success("根据id查询用户成功", USER_MAP.get(id));
    }

    @Override
    public CommonResult<User> updateUserById(User user) {
        if (user.getId() <= 0) {
            throw new InvalidParameterException("无效参数");
        }
        if (USER_MAP.get(user.getId()) == null) {
            throw new NotFoundException("用户不存在");
        }
        user = USER_MAP.get(user.getId()).setUsername(user.getUsername()).setPassword(user.getPassword());
        return CommonResult.success("根据id更新用户成功", user);
    }

    @Override
    public CommonResult<User> deleteUserById(Long id) {
        if (id <= 0) {
            throw new InvalidParameterException("无效参数");
        }
        if (USER_MAP.get(id) == null) {
            throw new NotFoundException("用户不存在");
        }
        return CommonResult.success("根据id删除用户成功", USER_MAP.remove(id));
    }

    @Override
    public CommonResult<List<User>> deleteUserAll() {
        if (USER_MAP.isEmpty()) {
            throw new NotFoundException("用户不存在");
        }
        ArrayList<User> users = new ArrayList<>(USER_MAP.values());
        USER_MAP.clear();
        return CommonResult.success("删除所有用户成功", users);
    }
}