package com.salaia.fulfillment_center.model;

import com.salaia.fulfillment_center.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
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
    private Double value;
}