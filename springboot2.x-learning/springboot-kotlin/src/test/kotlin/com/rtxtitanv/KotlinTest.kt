package com.rtxtitanv

import com.rtxtitanv.model.User
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @name com.rtxtitanv.KotlinTest
 * @description 单元测试类
 * @author rtxtitanv
 * @date 2020/1/16 15:33
 * @version 1.0.0
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [KotlinApplication::class])
class KotlinTest {

    private val logger: Logger = LoggerFactory.getLogger(KotlinTest::class.java)

    @Test
    fun kotlinTest() {
        //通过反射调用自动生成的无参构造函数
        val user1 = User::class.java.newInstance()
        //在User类主构造函数中初始化了属性值，则可以通过User()调用无参构造函数，省略no-args插件也可以，也可以反射调用
        //如果没有在主构造函数中初始化属性值，则不能通过User()显式调用无参构造函数，必须要加上应用no-args插件的注解
        //且通过反射调用
        val user2 = User()
        logger.info("user1: " + user1.toString())
        logger.info("user2: " + user2.toString())
    }
}