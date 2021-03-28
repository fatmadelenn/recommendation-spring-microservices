package com.fatmadelenn.recommendation.etl.batch;

import com.fatmadelenn.recommendation.etl.dto.OrderItemsDetails;
import com.fatmadelenn.recommendation.etl.entity.ProductsDetail;
import com.fatmadelenn.recommendation.etl.repository.ProductsDetailRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsDetailProcessor implements ItemProcessor<OrderItemsDetails, ProductsDetail> {

    @Autowired
    private ProductsDetailRepository productsDetailRepository;

    @Override
    public ProductsDetail process(final OrderItemsDetails orderItems) {
        List<ProductsDetail> productsDetailOptional = productsDetailRepository.findByProductId(orderItems.getProductId());
        int totalUser = 1;
        if (!productsDetailOptional.isEmpty()) {
            totalUser += productsDetailOptional.stream().map(ProductsDetail::getTotalUser).mapToInt(Integer::intValue).sum();
            productsDetailRepository.deleteAll(productsDetailOptional);
        }
        ProductsDetail productsDetail = new ProductsDetail();
        productsDetail.setProductId(orderItems.getProductId());
        productsDetail.setTotalUser(totalUser);
        return productsDetail;
    }
}
