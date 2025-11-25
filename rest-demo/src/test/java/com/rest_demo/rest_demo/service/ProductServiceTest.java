package com.rest_demo.rest_demo.service;

import com.rest_demo.rest_demo.model.Product;
import com.rest_demo.rest_demo.model.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductService - Pruebas unitarias")
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = Product.builder()
                .id(1L)
                .name("Teclado")
                .price(new BigDecimal("25.90"))
                .build();

        product2 = Product.builder()
                .id(2L)
                .name("Mouse")
                .price(new BigDecimal("15.50"))
                .build();
    }

    @Test
    @DisplayName("findAll() debería retornar lista de productos")
    void findAll_ShouldReturnListOfProducts() {
        // Given
        List<Product> expectedProducts = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(expectedProducts);

        // When
        List<Product> actualProducts = productService.findAll();

        // Then
        assertThat(actualProducts)
                .isNotNull()
                .hasSize(2)
                .containsExactlyInAnyOrder(product1, product2);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findAll() debería retornar lista vacía cuando no hay productos")
    void findAll_ShouldReturnEmptyList_WhenNoProducts() {
        // Given
        when(productRepository.findAll()).thenReturn(List.of());

        // When
        List<Product> actualProducts = productService.findAll();

        // Then
        assertThat(actualProducts)
                .isNotNull()
                .isEmpty();
        verify(productRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("save() debería guardar producto exitosamente")
    void save_ShouldSaveProductSuccessfully() {
        // Given
        Product newProduct = Product.builder()
                .name("Monitor")
                .price(new BigDecimal("199.99"))
                .build();

        Product savedProduct = Product.builder()
                .id(3L)
                .name("Monitor")
                .price(new BigDecimal("199.99"))
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // When
        Product result = productService.save(newProduct);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getName()).isEqualTo("Monitor");
        assertThat(result.getPrice()).isEqualByComparingTo(new BigDecimal("199.99"));
        verify(productRepository, times(1)).save(newProduct);
    }

    @Test
    @DisplayName("save() debería manejar productos con ID existente")
    void save_ShouldHandleExistingProductId() {
        // Given
        Product existingProduct = Product.builder()
                .id(1L)
                .name("Teclado Mecánico")
                .price(new BigDecimal("45.00"))
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        // When
        Product result = productService.save(existingProduct);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Teclado Mecánico");
        verify(productRepository, times(1)).save(existingProduct);
    }
}

