package com.rest_demo.rest_demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest_demo.rest_demo.model.Product;
import com.rest_demo.rest_demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@DisplayName("ProductController - Pruebas de integración")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
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
    @DisplayName("GET /api/v1/products debería retornar lista de productos")
    void getProductos_ShouldReturnListOfProducts() throws Exception {
        // Given
        List<Product> products = List.of(product1, product2);
        when(productService.findAll()).thenReturn(products);

        // When & Then
        mockMvc.perform(get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Teclado")))
                .andExpect(jsonPath("$[0].price", is(25.90)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Mouse")))
                .andExpect(jsonPath("$[1].price", is(15.50)));

        verify(productService, times(1)).findAll();
    }

    @Test
    @DisplayName("GET /api/v1/products debería retornar lista vacía cuando no hay productos")
    void getProductos_ShouldReturnEmptyList_WhenNoProducts() throws Exception {
        // Given
        when(productService.findAll()).thenReturn(Collections.emptyList());

        // When & Then
        mockMvc.perform(get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(productService, times(1)).findAll();
    }

    @Test
    @DisplayName("POST /api/v1/products debería crear producto exitosamente")
    void createProducto_ShouldCreateProductSuccessfully() throws Exception {
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

        when(productService.save(any(Product.class))).thenReturn(savedProduct);

        // When & Then
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Monitor")))
                .andExpect(jsonPath("$.price", is(199.99)));

        verify(productService, times(1)).save(any(Product.class));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidProductProvider")
    @DisplayName("POST /api/v1/products debería fallar con productos inválidos")
    void createProducto_ShouldFail_WhenProductIsInvalid(String testName, String name, BigDecimal price) throws Exception {
        // Given
        Product invalidProduct = Product.builder()
                .name(name)
                .price(price)
                .build();

        // When & Then
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidProduct)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(productService, never()).save(any(Product.class));
    }

    private static Stream<Arguments> invalidProductProvider() {
        return Stream.of(
                Arguments.of("nombre vacío", "", new BigDecimal("199.99")),
                Arguments.of("nombre muy corto", "AB", new BigDecimal("199.99")),
                Arguments.of("precio null", "Producto Sin Precio", null),
                Arguments.of("precio negativo", "Producto con Precio Negativo", new BigDecimal("-10.00")),
                Arguments.of("precio cero", "Producto con Precio Cero", new BigDecimal("0.00"))
        );
    }
}

