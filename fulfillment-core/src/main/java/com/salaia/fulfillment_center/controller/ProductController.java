package com.salaia.fulfillment_center.controller;

import com.salaia.fulfillment_center.dto.ProductDto;
import com.salaia.fulfillment_center.enums.ProductStatus;
import com.salaia.fulfillment_center.model.Product;
import com.salaia.fulfillment_center.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Products", description = "Requests for products")
@Validated
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    @Operation(summary = "Get a list of all products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    @Operation(summary = "Add a new product")
    public Product addProduct(Product product) {
        return productService.addProduct(product);
    }

    @PatchMapping
    @Operation(summary = "Update product information")
    public Product updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete product by productId")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/{status}")
    @Operation(summary = "Get a list of products with given status")
    public List<Product> getAllProductsByStatus(@PathVariable ProductStatus status) {
        return productService.getAllProductsByStatus(status);
    }

    @GetMapping("/sellable")
    @Operation(summary = "Get total value of all sellable products")
    public Double getValueOfSellableProducts() {
        return productService.getValueOfSellableProducts();
    }

    @GetMapping("/totalValue/{fulfillmentCenterId}")
    @Operation(summary = "Get total value of products placed in particular Fulfillment Center by Id")
    public Double getValueByFulfillmentCenterId(@PathVariable String fulfillmentCenterId) {
        return productService.getValueByFulfillmentCenterId(fulfillmentCenterId);
    }

}
