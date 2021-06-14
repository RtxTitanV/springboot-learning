package com.rtxtitanv.service.impl;

import com.rtxtitanv.exception.InvalidParameterException;
import com.rtxtitanv.exception.NotFoundException;
import com.rtxtitanv.model.CommonResult;
import com.rtxtitanv.model.Order;
import com.rtxtitanv.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.service.impl.OrderServiceImpl
 * @description OrderService实现类
 * @date 2021/6/8 15:45
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 创建线程安全的Map，模拟订单信息的存储
     */
    private static final Map<Long, Order> ORDER_MAP = Collections.synchronizedMap(new HashMap<>());

    @Override
    public CommonResult<Order> saveOrder(Order order) {
        if (order.getId() <= 0) {
            throw new InvalidParameterException("无效参数");
        }
        ORDER_MAP.put(order.getId(), order);
        return CommonResult.success("保存订单成功", order);
    }

    @Override
    public CommonResult<List<Order>> findOrderAll() {
        return CommonResult.success("查询所有订单成功", new ArrayList<>(ORDER_MAP.values()));
    }

    @Override
    public CommonResult<Order> updateOrderById(Order order) {
        if (order.getId() <= 0) {
            throw new InvalidParameterException("无效参数");
        }
        if (ORDER_MAP.get(order.getId()) == null) {
            throw new NotFoundException("订单不存在");
        }
        order = ORDER_MAP.get(order.getId()).setOrderNumber(order.getOrderNumber())
            .setOrderDescription(order.getOrderDescription()).setUserId(order.getUserId());
        return CommonResult.success("根据id更新订单成功", order);
    }

    @Override
    public CommonResult<Order> deleteOrderById(Long id) {
        if (id <= 0) {
            throw new InvalidParameterException("无效参数");
        }
        if (ORDER_MAP.get(id) == null) {
            throw new NotFoundException("订单不存在");
        }
        return CommonResult.success("根据id删除订单成功", ORDER_MAP.remove(id));
    }
}