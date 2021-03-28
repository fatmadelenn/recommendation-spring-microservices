package com.fatmadelenn.recommendation.viewproducer.dto;

public class Properties {

    private String productid;

    public Properties(String productid) {
        this.productid = productid;
    }

    public Properties() {
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }
}
