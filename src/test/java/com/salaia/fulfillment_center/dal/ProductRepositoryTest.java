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
        System.out.println("It lives!");
        List<Product> all = productRepository.findAll();
        List<Product> inboundProducts = productRepository.findAllByStatus(ProductStatus.INBOUND);
    }
}