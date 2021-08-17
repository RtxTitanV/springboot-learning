package com.rtxtitanv.controller;

import com.rtxtitanv.model.CommonResult;
import com.rtxtitanv.model.Order;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.controller.OrderController
 * @description OrderController
 * @date 2021/8/17 17:00
 */
@RequestMapping("/order")
@RestController
@Validated
public class OrderController {

    @PostMapping("/save")
    public CommonResult<Order> saveOrder(@RequestBody @Valid Order order) {
        return CommonResult.ok("订单保存成功", order);
    }
}