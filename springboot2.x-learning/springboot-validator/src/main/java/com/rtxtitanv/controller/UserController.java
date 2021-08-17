package com.rtxtitanv.controller;

import com.rtxtitanv.group.AddUserGroup;
import com.rtxtitanv.group.ModifyUserGroup;
import com.rtxtitanv.model.CommonResult;
import com.rtxtitanv.model.User;
import com.rtxtitanv.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.controller.UserController
 * @description UserController
 * @date 2021/8/15 16:33
 */
@RequestMapping("/user")
@RestController
@Validated
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private Validator validator;

    @PostMapping("/save")
    public CommonResult<User> saveUser(@RequestBody @Valid User user) {
        return CommonResult.ok("保存用户成功", user);
    }

    @GetMapping("/get/{id}")
    public CommonResult<User>
        getUserById(@Valid @PathVariable(value = "id") @Min(value = 1, message = "id不能小于1") Long id) {
        User user = new User(id, "ZhaoYun", "A123456sd", 20, "zhaoyun123@xxx.com", "黄金");
        return CommonResult.ok("根据id查询用户成功", user);
    }

    @DeleteMapping("/delete")
    public CommonResult<User> deleteByUsername(
        @Valid @RequestParam(value = "username") @Size(min = 6, max = 20, message = "用户名长度不在指定范围内") String username) {
        User user = new User(1L, username, "A123456sd", 20, "zhaoyun123@xxx.com", "黄金");
        return CommonResult.ok("根据用户名删除用户成功", user);
    }

    @PutMapping("/update")
    public CommonResult<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/insert")
    public CommonResult<User> insertUser(@RequestBody User user) {
        if (!validator.validate(user).isEmpty()) {
            throw new ConstraintViolationException(validator.validate(user));
        }
        return CommonResult.ok("添加用户成功", user);
    }

    @PostMapping("/add")
    public CommonResult<User> addUser(@RequestBody @Validated(value = AddUserGroup.class) User user) {
        return CommonResult.ok("新增用户成功", user);
    }

    @PutMapping("/modify")
    public CommonResult<User> modifyUser(@RequestBody @Validated(value = ModifyUserGroup.class) User user) {
        return CommonResult.ok("修改用户成功", user);
    }
}