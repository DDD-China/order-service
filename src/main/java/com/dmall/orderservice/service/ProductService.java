package com.dmall.orderservice.service;

import com.dmall.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(url = "http://product-service:9080", value = "product-service")
public interface ProductService {

    @RequestMapping("/products/{productId}")
    Product getProductDetial(@PathVariable("productId") String productId);
}

