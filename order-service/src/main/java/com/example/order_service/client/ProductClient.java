
package com.example.order_service.client;


import com.example.order_service.dto.ProductDto;
import com.example.order_service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", url = "http://product-service:8083",configuration = FeignConfig.class)
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductDto getProductById(@PathVariable("id") Long id);
}