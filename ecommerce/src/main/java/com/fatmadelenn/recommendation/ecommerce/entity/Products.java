package com.fatmadelenn.recommendation.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Products {

    @Id
    private String productId;

    @Column(length = 50)
    @NotNull
    private String categoryId;

    @OneToMany(targetEntity = OrderItems.class, mappedBy = "products", orphanRemoval = false, fetch = FetchType.LAZY)
    @JsonIgnore
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
