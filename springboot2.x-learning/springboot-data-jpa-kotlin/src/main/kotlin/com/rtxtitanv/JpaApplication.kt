package com.rtxtitanv

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @name com.rtxtitanv.JpaApplication
 * @description 主启动类
 * @author rtxtitanv
 * @date 2020/1/17 16:23
 * @version v1.0.0
 */
@SpringBootApplication
class JpaApplication

fun main(args: Array<String>) {
    runApplication<JpaApplication>(*args)
}