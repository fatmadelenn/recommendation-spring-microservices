package com.fatmadelenn.recommendation.ecommerce.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private Products products;

    @NotNull
    private Long quantity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
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
