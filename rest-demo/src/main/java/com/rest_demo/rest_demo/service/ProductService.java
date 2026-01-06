package com.rest_demo.rest_demo.service;

import com.rest_demo.rest_demo.model.Product;
import com.rest_demo.rest_demo.model.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        log.debug("Buscando todos los productos");
        List<Product> products = productRepository.findAll();
        log.debug("Se encontraron {} productos", products.size());
        return products;
    }

    public Product save(Product product) {
        log.debug("Guardando producto: {}", product);
        Product savedProduct = productRepository.save(product);
        log.info("Producto guardado exitosamente con ID: {}", savedProduct.getId());
        return savedProduct;
    }
}
