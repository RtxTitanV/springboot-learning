package com.rtxtitanv.controller.other;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.controller.other.ApiController
 * @description HelloController
 * @date 2021/6/8 17:03
 */
@Api(value = "HelloController", tags = "Hello World", description = "用于简单测试的API")
@RequestMapping("/hello")
@RestController
public class HelloController {

    @ApiOperation(value = "hello", notes = "一个简单的测试接口")
    @ApiImplicitParam(value = "名称", name = "name", required = true, defaultValue = "world")
    @GetMapping("/world/{name}")
    public String hello(@PathVariable(value = "name") String name) {
        return "hello " + name;
    }
}