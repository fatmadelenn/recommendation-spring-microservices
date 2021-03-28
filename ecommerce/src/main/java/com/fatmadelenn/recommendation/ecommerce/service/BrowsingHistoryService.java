package com.fatmadelenn.recommendation.ecommerce.service;

import com.fatmadelenn.recommendation.ecommerce.dto.DetailType;
import com.fatmadelenn.recommendation.ecommerce.dto.ProductResponse;
import com.fatmadelenn.recommendation.ecommerce.dto.Properties;
import com.fatmadelenn.recommendation.ecommerce.entity.ProductViews;
import com.fatmadelenn.recommendation.ecommerce.entity.Products;
import com.fatmadelenn.recommendation.ecommerce.exception.NotFoundException;
import com.fatmadelenn.recommendation.ecommerce.repository.OrderItemsRepository;
import com.fatmadelenn.recommendation.ecommerce.repository.ProductViewsRepository;
import com.fatmadelenn.recommendation.ecommerce.repository.ProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BrowsingHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(BrowsingHistoryService.class);

    @Autowired
    private ProductViewsRepository productViewsRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    public ProductResponse getLastTenProductsView(String userId) {
        Set<String> productIds = productViewsRepository.findTop10ByUseridOrderByTimestampDesc(userId)
                .stream().map(ProductViews::getProperties).map(Properties::getProductid).collect(Collectors.toSet());
        Set<Products> products = getAllProducts()
                .stream()
                .filter(value -> productIds.contains(value.getProductId())).collect(Collectors.toSet());
        Set<Products> productsSet = products.size() >= 5 ? products : new HashSet<>();
        return new ProductResponse(userId, productsSet, DetailType.PERSONALIZED);
    }

    public String deleteProductsView(String userId, String productId) {
        List<ProductViews> productViews = findByUseridAndPropertiesInProduct(userId, productId);
        if (CollectionUtils.isEmpty(productViews)) {
            throw new NotFoundException("ProductView not found!");
        }
        productViewsRepository.deleteAll(productViews);
        logger.info("Successfully deleted product from their history, products: {}", productViews);
        return String.format("Successfully deleted product from their history, product: %s", productViews);
    }

    @Cacheable("products")
    private List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    @Cacheable(value = "products", key = "{#userId, #productId}")
    private List<ProductViews> findByUseridAndPropertiesInProduct(String userId, String productId) {
        return productViewsRepository.findByUseridAndProperties(userId, new Properties(productId));
    }
}
