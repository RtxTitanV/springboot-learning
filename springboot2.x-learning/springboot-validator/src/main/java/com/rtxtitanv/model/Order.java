package com.rtxtitanv.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.Order
 * @description 订单实体类
 * @date 2021/8/17 16:55
 */
@Data
public class Order {
    @NotNull(message = "订单id不能为空")
    private Long orderId;
    @NotEmpty(message = "订单号不能为空")
    private String orderNumber;
    @NotEmpty(message = "订单描述信息不能为空")
    private String orderDescription;
    @Valid
    private Account account;
}