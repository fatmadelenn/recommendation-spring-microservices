package com.fatmadelenn.recommendation.ecommerce.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ProductsDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 50, unique = true)
    @NotNull
    private String productId;

    private int totalUser;

    public ProductsDetail() {
    }

    public ProductsDetail(@NotNull String productId, int totalUser) {
        this.productId = productId;
        this.totalUser = totalUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }
}
