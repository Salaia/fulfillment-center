package com.salaia.fulfillment_center.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salaia.fulfillment_center.dto.ProductDto;
import com.salaia.fulfillment_center.enums.ProductStatus;
import com.salaia.fulfillment_center.model.Product;
import com.salaia.fulfillment_center.service.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    ProductService productService;

    Product p1;
    ProductDto productDto;

    @BeforeEach
    void init() {
        p1 = new Product();
        p1.setId(1L);
        p1.setProductId("p1");
        p1.setStatus(ProductStatus.SELLABLE);
        p1.setFulfillmentCenter("fc5");
        p1.setQuantity(123L);
        p1.setValue(50.5);

        productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setProductId("p1");
        productDto.setStatus(ProductStatus.SELLABLE);
        productDto.setFulfillmentCenter("fc5");
        productDto.setQuantity(125L);
        productDto.setValue(50.5);

    }

    @Test
    void getAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(List.of(p1));
        mockMvc.perform(get("/products")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(status().isOk());
    }

    @Test
    void addProduct() throws Exception {
        when(productService.addProduct(any())).thenReturn(p1);
        mockMvc.perform(post("/products")
                        .content(mapper.writeValueAsString(p1))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId", is(p1.getProductId())))
                .andExpect(jsonPath("$.fulfillmentCenter", is(p1.getFulfillmentCenter())))
                .andExpect(jsonPath("$.quantity", is(p1.getQuantity()), Long.class))
                .andExpect(jsonPath("$.value", is(p1.getValue()), Double.class))
                .andExpect(status().isOk());
    }

    @Test
    void updateProduct() throws Exception {
        p1.setQuantity(productDto.getQuantity());
        when(productService.updateProduct(productDto)).thenReturn(p1);
        mockMvc.perform(patch("/products")
                        .content(mapper.writeValueAsString(p1))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId", is(p1.getProductId())))
                .andExpect(jsonPath("$.fulfillmentCenter", is(p1.getFulfillmentCenter())))
                .andExpect(jsonPath("$.quantity", is(p1.getQuantity()), Long.class))
                .andExpect(jsonPath("$.value", is(p1.getValue()), Double.class))
                .andExpect(status().isOk());
    }

    @Test
    void getAllProductsByStatus() throws Exception {
        when(productService.getAllProductsByStatus(ProductStatus.SELLABLE)).thenReturn(List.of(p1));
        mockMvc.perform(get("/products/SELLABLE")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(status().isOk());
    }

    @Test
    void getValueOfSellableProducts() throws Exception {
        when(productService.getValueOfSellableProducts()).thenReturn(p1.getValue());
        mockMvc.perform(get("/products/sellable")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(p1.getValue())))
                .andExpect(status().isOk());
    }

    @Test
    void getValueByFulfillmentCenterId() throws Exception {
        when(productService.getValueByFulfillmentCenterId("fc5")).thenReturn(p1.getValue());
        mockMvc.perform(get("/products/totalValue/fc5")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(p1.getValue())))
                .andExpect(status().isOk());

    }
}