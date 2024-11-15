package com.salaia.fulfillment_center.dal;

import com.salaia.fulfillment_center.enums.ProductStatus;
import com.salaia.fulfillment_center.model.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE)


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    ProductRepository productRepository;


    @Test
    public void findAllByStatusInboundSuccess() {
        List<Product> inboundProducts = productRepository.findAllByStatus(ProductStatus.INBOUND);
        for(Product product: inboundProducts) {
            assertEquals(ProductStatus.INBOUND, product.getStatus());
        }
    }

    @Test
    public void sumValueWhereSellableStatusSuccess() {
        Double sellableValue = productRepository.sumValueWhereSellableStatus(ProductStatus.SELLABLE);
        assertEquals(24870.0, sellableValue);
    }

    @Test
    public void sumValuesOfFulfillmentCenterSuccess() {
        Double sellableValue = productRepository.sumValuesOfFulfillmentCenter("fc5");
        assertEquals(12130.0, sellableValue);
    }

}