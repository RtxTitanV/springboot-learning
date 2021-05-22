package com.rtxtitanv

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @name com.rtxtitanv.KotlinApplication
 * @description 主启动类
 * @author rtxtitanv
 * @date 2020/1/16 14:48
 * @version 1.0.0
 */
@SpringBootApplication
class KotlinApplication

fun main(args: Array<String>) {
    runApplication<KotlinApplication>(*args)
}