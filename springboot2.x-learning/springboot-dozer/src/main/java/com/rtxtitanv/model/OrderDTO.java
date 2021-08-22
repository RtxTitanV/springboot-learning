package com.rtxtitanv.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.OrderDTO
 * @description OrderDTO
 * @date 2021/8/18 16:45
 */
@Accessors(chain = true)
@Data
public class OrderDTO {
    private Long orderId;
    private String orderNumber;
    private String orderDescription;
    private UserDTO userDTO;
}