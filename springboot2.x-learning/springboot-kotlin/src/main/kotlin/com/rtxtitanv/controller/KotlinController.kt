package com.rtxtitanv.controller

import com.rtxtitanv.model.User
import com.rtxtitanv.service.KotlinService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @name com.rtxtitanv.controller.KotlinController
 * @description KotlinController
 * @author rtxtitanv
 * @date 2020/1/16 14:59
 * @version 1.0.0
 */
@RequestMapping("/kotlin")
@RestController
class KotlinController(private val kotlinService: KotlinService) {
    /**
     * springboot2.x kotlin开发环境测试
     */
    @GetMapping("/test")
    fun helloKotlin(): String {
        return "hello kotlin!"
    }

    /**
     * 根据年龄查询用户
     */
    @GetMapping("/user/{age}")
    fun findUser(@PathVariable age: Int): List<User> {
        return kotlinService.findUserByAge(age)
    }
}

