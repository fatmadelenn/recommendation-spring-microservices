package com.fatmadelenn.recommendation.etl.batch;

import com.fatmadelenn.recommendation.etl.dto.OrderItemsDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemsRowMapper implements RowMapper<OrderItemsDetails> {

    @Override
    public OrderItemsDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItemsDetails orderItemsDetails = new OrderItemsDetails();
        orderItemsDetails.setCategoryId(rs.getString("category_id"));
        orderItemsDetails.setOrderId(rs.getLong("order_id"));
        orderItemsDetails.setProductId(rs.getString("product_id"));
        orderItemsDetails.setQuantity(rs.getLong("quantity"));
        orderItemsDetails.setUserId(rs.getString("user_id"));
        return orderItemsDetails;
    }
}

