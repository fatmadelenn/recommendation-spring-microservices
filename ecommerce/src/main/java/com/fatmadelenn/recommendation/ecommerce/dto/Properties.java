package com.fatmadelenn.recommendation.ecommerce.dto;

import javax.persistence.Embeddable;

@Embeddable
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

    @Override
    public String toString() {
        return "Properties{" +
                "productid='" + productid + '\'' +
                '}';
    }
}
