package com.rtxtitanv.controller;

import com.rtxtitanv.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.controller.UserController
 * @description UserController
 * @date 2021/8/23 14:54
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @PostMapping("/save")
    public User saveUser(User user) {
        return user;
    }

    @GetMapping("/get")
    public User getUserById(@RequestParam(value = "id") Long id) {
        return new User(id, "ZhaoYun", "123456");
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return user;
    }
}