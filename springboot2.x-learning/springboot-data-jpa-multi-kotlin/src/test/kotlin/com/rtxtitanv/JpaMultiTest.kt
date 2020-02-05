package com.rtxtitanv

import com.rtxtitanv.model.primary.PrimaryUser
import com.rtxtitanv.model.secondary.SecondaryUser
import com.rtxtitanv.repository.primary.PrimaryUserRepository
import com.rtxtitanv.repository.secondary.SecondaryUserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @name com.rtxtitanv.JpaMultiTest
 * @description SpringDataJpa多数据源测试类
 * @author rtxtitanv
 * @date 2020/2/5 19:03
 * @version v1.0.0
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [JpaMultiApplication::class])
class JpaMultiTest {

    @Autowired
    private lateinit var primaryUserRepository: PrimaryUserRepository
    @Autowired
    private lateinit var secondaryUserRepository: SecondaryUserRepository
    private val logger: Logger = LoggerFactory.getLogger(JpaMultiTest::class.java)

    /**
     * 多数据源保存测试
     */
    @Test
    fun saveTest() {
        primaryUserRepository.save(PrimaryUser(null, "aaa", "123456", "aaa", 20, "aaa@jpa.com", "13598766131"))
        primaryUserRepository.save(PrimaryUser(null, "bbb", "123123", "bbb", 22, "bbb@jpa.com", "13659836782"))
        primaryUserRepository.save(PrimaryUser(null, "ccc", "111111", "ccc", 25, "ccc@jpa.com", "18965233695"))
        secondaryUserRepository.save(SecondaryUser(null, "ddd", "2356890", "ccc", 18, "ddd@jpa.com", "13678922986"))
        secondaryUserRepository.save(SecondaryUser(null, "eee", "124678", "bbb", 25, "eee@jpa.com", "13669876321"))
        secondaryUserRepository.save(SecondaryUser(null, "fff", "112233567", "aaa", 22, "fff@jpa.com", "19862398732"))
    }

    /**
     * 多数据源查询测试
     */
    @Test
    fun findTest() {
        logger.info("查询主库user表测试开始")
        val primaryUsers = primaryUserRepository.findAll()
        if (primaryUsers.isEmpty()) {
            logger.info("主库user表不存在数据")
        } else {
            primaryUsers.forEach { primaryUser -> logger.info(primaryUser.toString()) }
        }
        logger.info("查询主库user表测试结束")
        logger.info("查询从库user表测试开始")
        val secondaryUsers = primaryUserRepository.findAll()
        if (secondaryUsers.isEmpty()) {
            logger.info("从库user表不存在数据")
        } else {
            secondaryUsers.forEach { secondaryUser -> logger.info(secondaryUser.toString()) }
        }
        logger.info("查询从库user表测试结束")
    }

    /**
     * 多数据源更新测试
     */
    @Test
    fun updateTest() {
        val user = primaryUserRepository.findById(1L)
        if (!user.isPresent) {
            logger.info("用户不存在")
        } else {
            user.get().userName = "ddd"
            user.get().nickName = "ddd"
            user.get().email = "ddd@jpa.com"
            primaryUserRepository.save(user.get())
        }
        val user1 = secondaryUserRepository.findById(1L)
        if (!user1.isPresent) {
            logger.info("用户记录不存在")
        } else {
            user1.get().userName = "aaa"
            user1.get().nickName = "aaa"
            user1.get().email = "aaa@jpa.com"
            secondaryUserRepository.save(user1.get())
        }
    }

    /**
     * 多数据源删除测试
     */
    @Test
    fun deleteTest() {
//        primaryUserRepository.deleteById(1L)
//        secondaryUserRepository.deleteById(3L)
        primaryUserRepository.deleteAllInBatch()
        secondaryUserRepository.deleteAllInBatch()
    }
}