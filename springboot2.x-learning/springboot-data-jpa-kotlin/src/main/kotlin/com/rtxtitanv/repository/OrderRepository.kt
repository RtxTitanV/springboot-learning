package com.rtxtitanv.repository

import com.rtxtitanv.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * @name com.rtxtitanv.repository.OrderRepository
 * @description OrderRepository接口用于操作orders表
 * @author rtxtitanv
 * @date 2020/1/20 22:32
 * @version 1.0.0
 */
interface OrderRepository : JpaRepository<Order, Long>, JpaSpecificationExecutor<Order>