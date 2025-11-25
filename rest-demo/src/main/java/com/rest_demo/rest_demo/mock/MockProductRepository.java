package com.rest_demo.rest_demo.mock;

import com.rest_demo.rest_demo.model.Product;
import com.rest_demo.rest_demo.model.ProductRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class MockProductRepository implements ProductRepository {

    private final Map<Long, Product> data = new HashMap<>();
    private long sequence = 3L;

    public MockProductRepository() {
        seed(new Product(1L, "Teclado", new BigDecimal("25.90")));
        seed(new Product(2L, "Mouse", new BigDecimal("15.50")));
        seed(new Product(3L, "Monitor", new BigDecimal("199.99")));
    }

    private void seed(Product product) {
        data.put(product.getId(), product);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(++sequence);
        }
        data.put(product.getId(), product);
        return product;
    }
}
