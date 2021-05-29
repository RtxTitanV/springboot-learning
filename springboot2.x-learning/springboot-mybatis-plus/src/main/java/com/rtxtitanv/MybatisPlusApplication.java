package com.rtxtitanv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.MybatisPlusApplication
 * @description 主启动类
 * @date 2021/5/16 15:37
 */
@SpringBootApplication
@MapperScan("com.rtxtitanv.mapper")
public class MybatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }
}