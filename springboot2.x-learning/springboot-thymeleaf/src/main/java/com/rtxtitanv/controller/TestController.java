package com.rtxtitanv.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.controller.TestController
 * @description TestController
 * @date 2021/7/3 19:43
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/hello")
    public ModelAndView hello() {
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("hello", "<h1>hello thymeleaf</h1>");
        return modelAndView;
    }
}