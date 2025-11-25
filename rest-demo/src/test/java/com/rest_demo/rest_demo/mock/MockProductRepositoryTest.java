package com.rest_demo.rest_demo.mock;

import com.rest_demo.rest_demo.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MockProductRepository - Pruebas unitarias")
class MockProductRepositoryTest {

    private MockProductRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MockProductRepository();
    }

    @Test
    @DisplayName("findAll() debería retornar los productos iniciales")
    void findAll_ShouldReturnInitialProducts() {
        // When
        List<Product> products = repository.findAll();

        // Then
        assertThat(products)
                .isNotNull()
                .hasSize(3)
                .extracting(Product::getName)
                .containsExactlyInAnyOrder("Teclado", "Mouse", "Monitor");
    }

    @Test
    @DisplayName("save() debería asignar ID a producto nuevo")
    void save_ShouldAssignIdToNewProduct() {
        // Given
        Product newProduct = Product.builder()
                .name("Auriculares")
                .price(new BigDecimal("35.00"))
                .build();

        // When
        Product savedProduct = repository.save(newProduct);

        // Then
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(3L);
        assertThat(savedProduct.getName()).isEqualTo("Auriculares");
        assertThat(savedProduct.getPrice()).isEqualByComparingTo(new BigDecimal("35.00"));
    }

    @Test
    @DisplayName("save() debería mantener el ID de producto existente")
    void save_ShouldKeepExistingProductId() {
        // Given
        Product existingProduct = Product.builder()
                .id(1L)
                .name("Teclado Mecánico")
                .price(new BigDecimal("45.00"))
                .build();

        // When
        Product savedProduct = repository.save(existingProduct);

        // Then
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isEqualTo(1L);
        assertThat(savedProduct.getName()).isEqualTo("Teclado Mecánico");
        assertThat(savedProduct.getPrice()).isEqualByComparingTo(new BigDecimal("45.00"));
    }

    @Test
    @DisplayName("save() debería actualizar producto existente")
    void save_ShouldUpdateExistingProduct() {
        // Given - buscar producto inicial
        List<Product> initialProducts = repository.findAll();
        int initialSize = initialProducts.size();

        Product updatedProduct = Product.builder()
                .id(2L)
                .name("Mouse Inalámbrico")
                .price(new BigDecimal("25.00"))
                .build();

        // When
        repository.save(updatedProduct);
        List<Product> afterUpdate = repository.findAll();

        // Then
        assertThat(afterUpdate).hasSize(initialSize); // No se agregó nuevo producto
        Product found = afterUpdate.stream()
                .filter(p -> p.getId().equals(2L))
                .findFirst()
                .orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Mouse Inalámbrico");
        assertThat(found.getPrice()).isEqualByComparingTo(new BigDecimal("25.00"));
    }

    @Test
    @DisplayName("save() debería incrementar el contador de secuencia correctamente")
    void save_ShouldIncrementSequenceCorrectly() {
        // Given
        Product product1 = Product.builder()
                .name("Producto 1")
                .price(new BigDecimal("10.00"))
                .build();

        Product product2 = Product.builder()
                .name("Producto 2")
                .price(new BigDecimal("20.00"))
                .build();

        // When
        Product saved1 = repository.save(product1);
        Product saved2 = repository.save(product2);

        // Then
        assertThat(saved1.getId()).isNotNull();
        assertThat(saved2.getId()).isNotNull();
        assertThat(saved2.getId()).isGreaterThan(saved1.getId());
    }

    @Test
    @DisplayName("findAll() debería retornar todos los productos incluyendo nuevos")
    void findAll_ShouldReturnAllProductsIncludingNew() {
        // Given
        Product newProduct = Product.builder()
                .name("WebCam")
                .price(new BigDecimal("50.00"))
                .build();

        // When
        repository.save(newProduct);
        List<Product> products = repository.findAll();

        // Then
        assertThat(products).hasSize(4);
        assertThat(products).extracting(Product::getName)
                .contains("WebCam");
    }

    @Test
    @DisplayName("repository debería ser thread-safe con ConcurrentHashMap")
    void repository_ShouldBeThreadSafe() {
        // Given
        Product product1 = Product.builder()
                .name("Producto Thread 1")
                .price(new BigDecimal("10.00"))
                .build();

        Product product2 = Product.builder()
                .name("Producto Thread 2")
                .price(new BigDecimal("20.00"))
                .build();

        // When - Simular operaciones concurrentes
        Thread thread1 = new Thread(() -> repository.save(product1));
        Thread thread2 = new Thread(() -> repository.save(product2));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Then
        List<Product> products = repository.findAll();
        assertThat(products).hasSizeGreaterThanOrEqualTo(5); // 3 iniciales + 2 nuevos
    }
}

