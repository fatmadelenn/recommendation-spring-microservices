package com.fatmadelenn.recommendation.ecommerce.dto;

import com.fatmadelenn.recommendation.ecommerce.entity.Products;

import java.util.Set;

public class ProductResponse {

    private String userId;
    private Set<Products> products;
    private DetailType type;

    public ProductResponse() {
    }

    public ProductResponse(String userId, Set<Products> products, DetailType type) {
        this.userId = userId;
        this.products = products;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<Products> getProducts() {
        return products;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;
    }

    public DetailType getType() {
        return type;
    }

    public void setType(DetailType type) {
        this.type = type;
    }
}
