package com.fatmadelenn.recommendation.ecommerce.controller;

import com.fatmadelenn.recommendation.ecommerce.dto.ProductResponse;
import com.fatmadelenn.recommendation.ecommerce.service.BestSellerProductsService;
import com.fatmadelenn.recommendation.ecommerce.service.BrowsingHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(value = "Recommendation REST API", description = "Recommendation REST Endpoints")
public class ProductViewsController {

    @Autowired
    private BrowsingHistoryService browsingHistoryService;

    @Autowired
    private BestSellerProductsService bestSellerProductsService;

    @ApiOperation(value = "This list the last ten products viewed by a given user and sorted by view date", response = ProductResponse.class)
    @GetMapping("history")
    private ProductResponse getLastTenProductsView(@RequestParam String userId) {
        return browsingHistoryService.getLastTenProductsView(userId);
    }

    @ApiOperation(value = "It can delete a product from their history")
    @DeleteMapping("history/delete")
    private String deleteProductsView(@RequestParam String userId, @RequestParam String productId) {
        return browsingHistoryService.deleteProductsView(userId, productId);
    }

    @ApiOperation(value = "This list bestsellers of a category of products", response = ProductResponse.class)
    @GetMapping("best-seller")
    private ProductResponse getBestSellerProduct(@RequestParam String userId) {
        return bestSellerProductsService.getBestSellerProduct(userId);
    }
}
