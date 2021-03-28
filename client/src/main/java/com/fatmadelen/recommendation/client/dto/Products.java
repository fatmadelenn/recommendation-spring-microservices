package com.fatmadelen.recommendation.client.dto;

import java.util.Set;

public class Products {

    private String productId;
    private String categoryId;
    private Set<OrderItems> orderItems;

    public Products() {
    }

    public String getProductId() {
        return productId;
    }

    public Set<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productId='" + productId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}
