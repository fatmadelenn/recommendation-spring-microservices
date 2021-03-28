package com.fatmadelen.recommendation.client.dto;

public class Orders {

    private Long orderId;
    private String userId;
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
