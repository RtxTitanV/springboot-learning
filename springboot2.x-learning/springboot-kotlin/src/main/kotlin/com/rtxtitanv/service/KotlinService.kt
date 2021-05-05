package com.rtxtitanv.service

import com.rtxtitanv.model.User

/**
 * @name com.rtxtitanv.service.KotlinService
 * @description KotlinService接口
 * @author rtxtitanv
 * @date 2020/1/16 17:06
 * @version 1.0.0
 */
interface KotlinService {

    /**
     * 根据年龄查询用户
     */
    fun findUserByAge(age: Int): List<User>
}