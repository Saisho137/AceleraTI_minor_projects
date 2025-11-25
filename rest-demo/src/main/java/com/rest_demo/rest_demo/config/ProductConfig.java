package com.rest_demo.rest_demo.config;

import com.rest_demo.rest_demo.model.ProductRepository;
import com.rest_demo.rest_demo.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    private final ProductRepository productRepository;

    public ProductConfig(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    public ProductService productService() {
        return new ProductService(productRepository);
    }
}

