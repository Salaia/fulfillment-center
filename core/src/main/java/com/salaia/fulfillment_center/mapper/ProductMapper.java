package com.salaia.fulfillment_center.mapper;

import com.salaia.fulfillment_center.dto.ProductDto;
import com.salaia.fulfillment_center.model.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

@UtilityClass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductMapper {

    public Product toProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setStatus(productDto.getStatus());
        product.setFulfillmentCenter(productDto.getFulfillmentCenter());
        product.setQuantity(productDto.getQuantity());
        product.setValue(productDto.getValue());
        return product;
    }

    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setStatus(product.getStatus());
        productDto.setFulfillmentCenter(product.getFulfillmentCenter());
        productDto.setQuantity(product.getQuantity());
        productDto.setValue(product.getValue());
        return productDto;
    }
}
