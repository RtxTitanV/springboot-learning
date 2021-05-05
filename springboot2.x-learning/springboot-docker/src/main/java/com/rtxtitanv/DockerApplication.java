package com.rtxtitanv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.DockerApplication
 * @description 主启动类
 * @date 2020/5/14 14:23
 */
@RestController
@SpringBootApplication
public class DockerApplication {

    @GetMapping("/")
    public String home() {
        return "Hello Docker World";
    }

    public static void main(String[] args) {
        SpringApplication.run(DockerApplication.class, args);
    }
}
