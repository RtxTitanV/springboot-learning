package com.rtxtitanv.model

import javax.persistence.*

/**
 * @name com.rtxtitanv.model.Order
 * @description 订单实体类 ManyToOne 一对多的多方
 * @author rtxtitanv
 * @date 2020/1/20 21:08
 * @version 1.0.0
 */
@Entity
@Table(name = "orders") // 注意表名不能为order，自动建表时会报错
class Order(@Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "orders_id")
            var orderId: Long? = null,
            @Column(name = "orders_name", nullable = false)
            var orderName: String? = null,
            @Column(name = "orders_description")
            var orderDescription: String? = null,
            @Column(name = "orders_status", nullable = false)
            var orderStatus: String? = null,
            @Column(name = "orders_total_price", nullable = false)
            var orderTotalPrice: String? = null,
            @Column(name = "orders_item_count", nullable = false)
            var orderItemCount: Int? = null,
            @Column(name = "orders_addr", nullable = false)
            var orderAddr: String? = null,
            // FetchType.EAGER 立即加载
            @ManyToOne(cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
            // 定义主键字段和外键字段对应关系，name：外键字段名称，nullable：是否允许为空
            @JoinColumn(name = "orders_account_id", nullable = false)
            var account: Account? = null) {

    override fun toString(): String {
        return "Order{" +
                "orderId=$orderId" +
                ", orderName='$orderName'" +
                ", orderDescription='$orderDescription'" +
                ", orderStatus='$orderStatus'" +
                ", orderTotalPrice='$orderTotalPrice'" +
                ", orderItemCount=$orderItemCount" +
                ", orderAddr='$orderAddr'" +
                "}"
    }
}