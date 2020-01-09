package com.rtxtitanv.repository;

import com.rtxtitanv.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author rtxtitanv
 * @version v1.0.0
 * @name com.rtxtitanv.repository.OrderRepository
 * @description OrderRepository接口用于操作orders表
 * @date 2020/1/3 21:42
 */
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
}
