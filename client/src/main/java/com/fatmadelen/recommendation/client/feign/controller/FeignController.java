package com.fatmadelen.recommendation.client.feign.controller;

import com.fatmadelen.recommendation.client.dto.DeleteProductDto;
import com.fatmadelen.recommendation.client.dto.UserDto;
import com.fatmadelen.recommendation.client.feign.client.FeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@EnableFeignClients
@Controller
public class FeignController {

    @Autowired
    private FeignClientService feignClientService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("searchDto", new UserDto());
        return "index";
    }

    @GetMapping("history")
    public String getLastTenProductsView(@ModelAttribute UserDto searchDto, BindingResult result, Model model) {
        model.addAttribute("deleteProductDto", new DeleteProductDto());
        model.addAttribute("productList", feignClientService.getLastTenProductsView(searchDto.getUserId()));
        return "products";
    }

    @DeleteMapping("history/delete")
    public String deleteProductsView(@ModelAttribute DeleteProductDto deleteProductDto, BindingResult result, Model model) {
        model.addAttribute("deleteProductsView", feignClientService.deleteProductsView(deleteProductDto.getUserId(), deleteProductDto.getProductId()));
        return "redirect:/history?userId=" + deleteProductDto.getUserId();
    }

    @GetMapping("best-seller")
    public String getBestSellerProduct(@ModelAttribute UserDto searchDto, BindingResult result, Model model) {
        model.addAttribute("bestSellerList", feignClientService.getBestSellerProduct(searchDto.getUserId()));
        return "bestseller";
    }
}
