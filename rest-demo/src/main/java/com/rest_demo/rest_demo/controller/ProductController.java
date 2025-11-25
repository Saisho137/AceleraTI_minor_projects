package com.rest_demo.rest_demo.controller;

import com.rest_demo.rest_demo.model.Product;
import com.rest_demo.rest_demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getProductos() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Product> createProducto(@RequestBody Product product) {
        Product saved = service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
