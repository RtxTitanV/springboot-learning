package com.rtxtitanv.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.Order
 * @description 订单实体类
 * @date 2021/6/8 11:30
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "Order", description = "订单信息")
public class Order implements Serializable {

    private static final long serialVersionUID = -688022729580581140L;
    @ApiModelProperty(value = "订单id")
    private Long id;
    @ApiModelProperty(value = "订单编号")
    private String orderNumber;
    @ApiModelProperty(value = "订单描述")
    private String orderDescription;
    @ApiModelProperty(value = "订单所属用户id")
    private Long userId;
}