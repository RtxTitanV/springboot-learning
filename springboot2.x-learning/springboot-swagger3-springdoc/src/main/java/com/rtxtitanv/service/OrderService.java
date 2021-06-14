package com.rtxtitanv.service;

import com.rtxtitanv.model.CommonResult;
import com.rtxtitanv.model.Order;

import java.util.List;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.service.OrderService
 * @description OrderService
 * @date 2021/6/8 17:20
 */
public interface OrderService {

    /**
     * 保存订单
     *
     * @param order 订单参数
     * @return CommonResult<Order>
     */
    CommonResult<Order> saveOrder(Order order);

    /**
     * 查询所有订单
     *
     * @return CommonResult<List<Order>>
     */
    CommonResult<List<Order>> findOrderAll();

    /**
     * 根据id更新订单
     *
     * @param order 订单参数
     * @return CommonResult<Order>
     */
    CommonResult<Order> updateOrderById(Order order);

    /**
     * 根据id删除订单
     *
     * @param id 订单id
     * @return CommonResult<Order>
     */
    CommonResult<Order> deleteOrderById(Long id);
}