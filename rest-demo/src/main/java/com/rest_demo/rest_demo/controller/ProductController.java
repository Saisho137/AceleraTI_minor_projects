package com.rest_demo.rest_demo.controller;

import com.rest_demo.rest_demo.model.Product;
import com.rest_demo.rest_demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getProductos() {
        log.info("Obteniendo todos los productos");
        List<Product> products = service.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> createProducto(@Valid @RequestBody Product product) {
        log.info("Creando nuevo producto: {}", product.getName());
        Product saved = service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
