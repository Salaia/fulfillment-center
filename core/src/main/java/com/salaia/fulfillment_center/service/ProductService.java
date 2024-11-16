package com.salaia.fulfillment_center.service;

import com.salaia.fulfillment_center.dal.ProductRepository;
import com.salaia.fulfillment_center.dto.ProductDto;
import com.salaia.fulfillment_center.enums.ProductStatus;
import com.salaia.fulfillment_center.mapper.ProductMapper;
import com.salaia.fulfillment_center.model.Product;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
        if (optionalProduct.isEmpty()) {
            throw new EntityNotFoundException("Not found: product by ID " + productDto.getId());
        }

        Product product = optionalProduct.get();
        if (productDto.getStatus() != null &&
                !productDto.getStatus().equals(product.getStatus())) {
            product.setStatus(productDto.getStatus());
        }
        if (productDto.getFulfillmentCenter() != null &&
                !productDto.getFulfillmentCenter().equals(product.getFulfillmentCenter())) {
            product.setFulfillmentCenter(productDto.getFulfillmentCenter());
        }
        if (productDto.getQuantity() != null && !productDto.getQuantity().equals(product.getQuantity())) {
            product.setQuantity(productDto.getQuantity());
        }
        if (productDto.getValue() != null && !productDto.getValue().equals(product.getValue())) {
            product.setValue(productDto.getValue());
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) productRepository.delete(optionalProduct.get());
        else throw new EntityNotFoundException("Not found: product by ID " + productId);
    }

    public List<Product> getAllProductsByStatus(ProductStatus status) {
        return productRepository.findAllByStatus(status);
    }

    public Double getValueOfSellableProducts() {
        return productRepository.sumValueWhereSellableStatus(ProductStatus.SELLABLE);
    }

    public Double getValueByFulfillmentCenterId(String fulfillmentCenterId) {
        return productRepository.sumValuesOfFulfillmentCenter(fulfillmentCenterId);
    }


}
