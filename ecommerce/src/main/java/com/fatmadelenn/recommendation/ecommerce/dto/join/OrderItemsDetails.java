package com.fatmadelenn.recommendation.ecommerce.dto.join;

public class OrderItemsDetails {

    private Long orderId;
    private String userId;
    private String productId;
    private String categoryId;
    private Long quantity;

    public OrderItemsDetails(Long orderId, String userId, String productId, String categoryId, Long quantity) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.categoryId = categoryId;
        this.quantity = quantity;
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

    public String getProductId() {
        return productId;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemsDetails{" +
                "orderId=" + orderId +
                ", userId='" + userId + '\'' +
                ", productId='" + productId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
