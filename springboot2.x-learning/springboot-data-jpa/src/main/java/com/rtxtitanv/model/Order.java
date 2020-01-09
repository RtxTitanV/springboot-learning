package com.rtxtitanv.model;

import javax.persistence.*;

/**
 * @author rtxtitanv
 * @version v1.0.0
 * @name com.rtxtitanv.model.Order
 * @description 订单实体类 ManyToOne 一对多的多方
 * @date 2020/1/3 20:33
 */
@Entity
@Table(name = "orders") //注意表名不能为order,自动建表时会报错
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long orderId;
    @Column(name = "orders_name", nullable = false)
    private String orderName;
    @Column(name = "orders_description")
    private String orderDescription;
    @Column(name = "orders_status", nullable = false)
    private String orderStatus;
    @Column(name = "orders_total_price", nullable = false)
    private String orderTotalPrice;
    @Column(name = "orders_item_count", nullable = false)
    private Integer orderItemCount;
    @Column(name = "orders_addr", nullable = false)
    private String orderAddr;
    //FetchType.EAGER 立即加载
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    //定义主键字段和外键字段对应关系,name:外键字段名称, nullable:是否允许为空
    @JoinColumn(name = "orders_account_id", nullable = false)
    private Account account;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(String orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public Integer getOrderItemCount() {
        return orderItemCount;
    }

    public void setOrderItemCount(Integer orderItemCount) {
        this.orderItemCount = orderItemCount;
    }

    public String getOrderAddr() {
        return orderAddr;
    }

    public void setOrderAddr(String orderAddr) {
        this.orderAddr = orderAddr;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderTotalPrice='" + orderTotalPrice + '\'' +
                ", orderItemCount=" + orderItemCount +
                ", orderAddr='" + orderAddr + '\'' +
                '}';
    }
}
