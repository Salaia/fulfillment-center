package com.salaia.fulfillment_center.model;

import com.salaia.fulfillment_center.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "product_id")
    private String productId;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @Column(name = "fulfillment_center")
    private String fulfillmentCenter;
    private Long quantity;
    @Column(name = "price")
    private Double value;

    public Product(String productId, ProductStatus status, String fulfillmentCenter, Long quantity, Double value) {
        this.productId = productId;
        this.status = status;
        this.fulfillmentCenter = fulfillmentCenter;
        this.quantity = quantity;
        this.value = value;
    }
}