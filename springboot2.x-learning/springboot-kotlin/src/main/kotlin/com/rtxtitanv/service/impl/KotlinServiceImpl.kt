package com.rtxtitanv.service.impl

import com.rtxtitanv.model.User
import com.rtxtitanv.service.KotlinService
import org.springframework.stereotype.Service

/**
 * @name com.rtxtitanv.service.impl.KotlinServiceImpl
 * @description KotlinService实现类
 * @author rtxtitanv
 * @date 2020/1/16 17:07
 * @version v1.0.0
 */
@Service
class KotlinServiceImpl : KotlinService {
    /**
     * 模拟根据年龄查询用户,只是测试,返回写死的user数据
     */
    override fun findUserByAge(age: Int): List<User> {
        val user1 = User(1L, "aaa",
                "123456", "abc",
                age, "198658632", "北京")
        val user2 = User(2L, "bbb",
                "111222", "cba",
                age, "15963247851", "上海")
        val userList = ArrayList<User>()
        userList.add(user1)
        userList.add(user2)
        return userList
    }
}