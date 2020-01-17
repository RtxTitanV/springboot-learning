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
 * @version v1.0.0
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [KotlinApplication::class])
class KotlinTest {

    private val logger: Logger = LoggerFactory.getLogger(KotlinTest::class.java)

    @Test
    fun kotlinTest() {
        //通过反射调用no-arg插件生成的无参构造函数
        val user = User::class.java.newInstance()
        logger.info(user.toString())
    }
}