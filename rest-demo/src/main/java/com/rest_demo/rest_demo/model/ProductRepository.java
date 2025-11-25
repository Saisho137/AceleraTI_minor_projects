package com.rest_demo.rest_demo.model;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product save(Product product);
}
