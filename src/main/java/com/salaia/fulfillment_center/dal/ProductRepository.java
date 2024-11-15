package com.salaia.fulfillment_center.dal;

import com.salaia.fulfillment_center.enums.ProductStatus;
import com.salaia.fulfillment_center.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByStatus(ProductStatus status);

    @Query("SELECT SUM(p.value) FROM Product AS p WHERE p.status = :status")
    Double sumValueWhereSellableStatus(ProductStatus status);

    @Query("SELECT SUM(p.value) FROM Product AS p WHERE p.fulfillmentCenter = :center")
    Double sumValuesOfFulfillmentCenter(String center);
}
