package com.fatmadelen.recommendation.client.feign.client;

import com.fatmadelen.recommendation.client.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ecommerce-api")
public interface FeignClientService {

    @RequestMapping(method = RequestMethod.GET, value = "/api/history")
    ProductResponse getLastTenProductsView(@RequestParam String userId);

    @DeleteMapping("/api/history/delete")
    String deleteProductsView(@RequestParam String userId, @RequestParam String productId);

    @RequestMapping(method = RequestMethod.GET, value = "/api/best-seller")
    ProductResponse getBestSellerProduct(@RequestParam String userId);
}
