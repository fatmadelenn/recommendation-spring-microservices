package com.fatmadelenn.recommendation.ecommerce.service;

import com.fatmadelenn.recommendation.ecommerce.dto.DetailType;
import com.fatmadelenn.recommendation.ecommerce.dto.ProductResponse;
import com.fatmadelenn.recommendation.ecommerce.dto.Properties;
import com.fatmadelenn.recommendation.ecommerce.dto.join.OrderItemsDetails;
import com.fatmadelenn.recommendation.ecommerce.entity.ProductViews;
import com.fatmadelenn.recommendation.ecommerce.entity.Products;
import com.fatmadelenn.recommendation.ecommerce.repository.OrderItemsRepository;
import com.fatmadelenn.recommendation.ecommerce.repository.ProductViewsRepository;
import com.fatmadelenn.recommendation.ecommerce.repository.ProductsDetailRepository;
import com.fatmadelenn.recommendation.ecommerce.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class BestSellerProductsService {

    @Autowired
    private ProductViewsRepository productViewsRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ProductsDetailRepository productsDetailRepository;

    public ProductResponse getBestSellerProduct(String userId) {
        Set<Products> products = new HashSet<>();
        DetailType type;
        List<ProductViews> history = productViewsRepository.findByUserid(userId);
        if (!CollectionUtils.isEmpty(history)) {
            Map<String, Integer> getHistoryCategories = getCategoryMap(history);
            List<Map.Entry<String, Integer>> atMostThreeCategories;
            try {
                atMostThreeCategories = compareMapValue(getHistoryCategories).subList(0, 3);
            } catch (IndexOutOfBoundsException e) {
                atMostThreeCategories = compareMapValue(getHistoryCategories).subList(0, getCategoryMap(history).size());
            }
            atMostThreeCategories.forEach(value -> {
                List<String> bestSellerProductsWithCategory = productsDetailRepository.orderDescestSellerWithCategory(value.getKey());
                setBestSellerProductsSet(bestSellerProductsWithCategory, products);
            });
            type = DetailType.PERSONALIZED;
        } else {
            List<String> bestSellerProducts = productsDetailRepository.orderDescBestSeller();
            setBestSellerProductsSet(bestSellerProducts, products);
            type = DetailType.NON_PERSONALIZED;
        }
        Set<Products> productsSet = products.size() >= 5 ? products : new HashSet<>();
        return new ProductResponse(userId, productsSet, type);
    }

    private Map<String, Integer> getCategoryMap(List<ProductViews> history) {
        Map<String, Integer> categoryMap = new HashMap<>();
        history.stream()
                .map(ProductViews::getProperties)
                .map(Properties::getProductid)
                .forEach(value -> {
                    Products product = productsRepository.findByProductId(value).get();
                    int categoryCount = 1;
                    if (categoryMap.containsKey(product.getCategoryId())) {
                        categoryCount += categoryMap.get(product.getCategoryId());
                    }
                    categoryMap.put(product.getCategoryId(), categoryCount);
                });
        return categoryMap;
    }

    private void setBestSellerProductsSet(List<String> products, Set<Products> productsSet) {
        try {
            products.subList(0, 10).forEach(countMap -> productsSet.add(productsRepository.findByProductId(countMap).get()));
        } catch (IndexOutOfBoundsException e) {
            products.subList(0, products.size()).forEach(countMap -> productsSet.add(productsRepository.findByProductId(countMap).get()));
        }
    }

    private List<Map.Entry<String, Integer>> compareMapValue(Map map) {
        Set set = map.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(set);
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return list;
    }

}
