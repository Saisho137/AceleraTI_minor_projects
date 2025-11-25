package com.rest_demo.rest_demo.mock;

import com.rest_demo.rest_demo.model.Product;
import com.rest_demo.rest_demo.model.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Slf4j
public class MockProductRepository implements ProductRepository {

    private final Map<Long, Product> data = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(3L);

    public MockProductRepository() {
        log.info("Inicializando MockProductRepository con datos de prueba");
        seed(Product.builder().id(1L).name("Teclado").price(new BigDecimal("25.90")).build());
        seed(Product.builder().id(2L).name("Mouse").price(new BigDecimal("15.50")).build());
        seed(Product.builder().id(3L).name("Monitor").price(new BigDecimal("199.99")).build());
    }

    private void seed(Product product) {
        data.put(product.getId(), product);
        log.debug("Producto agregado a la BD en memoria: {}", product);
    }

    @Override
    public List<Product> findAll() {
        log.debug("findAll() - retornando {} productos", data.size());
        return new ArrayList<>(data.values());
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(sequence.incrementAndGet());
            log.debug("Generando nuevo ID para producto: {}", product.getId());
        }
        data.put(product.getId(), product);
        log.debug("Producto guardado: {}", product);
        return product;
    }
}
