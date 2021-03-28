package com.fatmadelen.recommendation.client.dto;

import javax.validation.constraints.NotNull;

public class OrderItems {

    private Integer id;
    private Products products;
    @NotNull
    private Long quantity;
    private Orders orders;

    public OrderItems() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "id=" + id +
                ", products=" + products +
                ", quantity=" + quantity +
                ", orders=" + orders +
                '}';
    }
}
