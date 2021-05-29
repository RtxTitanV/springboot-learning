package com.rtxtitanv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.RabbitMqConsumerApplication
 * @description 主启动类
 * @date 2021/4/17 14:51
 */
@SpringBootApplication
public class RabbitMqConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqConsumerApplication.class, args);
    }
}