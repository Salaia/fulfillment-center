package com.salaia.fulfillment_center.dto;

import com.salaia.fulfillment_center.enums.ProductStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * Основное отличие от сущности Product: все поля не обязательны, кроме id -
 * используется для обновления
 */

@Data
public class ProductDto {
    @NotEmpty
    Long id;

    String productId; // Артикул товара
    ProductStatus status;
    String fulfillmentCenter;
    Long quantity;
    Double value; // стоимость
}
