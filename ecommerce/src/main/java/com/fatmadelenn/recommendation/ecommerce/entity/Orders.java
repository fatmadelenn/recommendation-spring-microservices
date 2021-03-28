package com.fatmadelenn.recommendation.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Column(length = 50)
    @NotNull
    private String userId;

    @OneToOne(targetEntity = OrderItems.class, mappedBy = "orders", orphanRemoval = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private OrderItems orderItems;

    public Orders() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItems orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", userId='" + userId + '\'' +
                '}';
    }
}
