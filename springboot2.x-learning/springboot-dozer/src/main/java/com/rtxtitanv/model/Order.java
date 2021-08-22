package com.rtxtitanv.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.Order
 * @description Order
 * @date 2021/8/18 16:45
 */
@Accessors(chain = true)
@Data
public class Order {
    private Long id;
    private String number;
    private String description;
    private User user;
}