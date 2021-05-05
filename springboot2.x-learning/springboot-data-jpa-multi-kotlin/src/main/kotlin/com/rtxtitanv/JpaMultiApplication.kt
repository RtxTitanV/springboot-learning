package com.rtxtitanv

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @name com.rtxtitanv.JpaMultiApplication
 * @description 主启动类
 * @author rtxtitanv
 * @date 2020/2/5 16:21
 * @version 1.0.0
 */
@SpringBootApplication
class JpaMultiApplication

fun main(args: Array<String>) {
    runApplication<JpaMultiApplication>(*args)
}