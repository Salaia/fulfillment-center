package com.salaia.fulfillment_center.dal;

import com.salaia.fulfillment_center.enums.ProductStatus;
import com.salaia.fulfillment_center.model.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@FieldDefaults(level = AccessLevel.PRIVATE)


@RunWith(SpringRunner.class)
@DataJpaTest(properties = {
        "spring.sql.init.mode=never",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.url=jdbc:h2:mem:fulfillment",
        "spring.datasource.username=test",
        "spring.datasource.password=test",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class ProductRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    ProductRepository productRepository;

    static List<Product> initialProducts = new ArrayList<>(29);

    @BeforeAll
    public static void createObjectList() {
        initialProducts.add(new Product("p1", ProductStatus.SELLABLE, "fc5", 4L, 400.0));
        initialProducts.add(new Product("p2", ProductStatus.UNFULFILLABLE, "fc3", 5L, 550.0));
        initialProducts.add(new Product("p3", ProductStatus.INBOUND, "fc4", 15L, 1800.0));
        initialProducts.add(new Product("p5", ProductStatus.SELLABLE, "fc1", 22L, 3080.0));
        initialProducts.add(new Product("p1", ProductStatus.INBOUND, "fc5", 15L, 2850.0));
        initialProducts.add(new Product("p1", ProductStatus.UNFULFILLABLE, "fc2", 10L, 1000.0));
        initialProducts.add(new Product("p8", ProductStatus.SELLABLE, "fc1", 5L, 850.0));
        initialProducts.add(new Product("p3", ProductStatus.SELLABLE, "fc2", 2L, 240.0));
        initialProducts.add(new Product("p5", ProductStatus.SELLABLE, "fc5", 9L, 1260.0));
        initialProducts.add(new Product("p3", ProductStatus.SELLABLE, "fc4", 25L, 3000.0));
        initialProducts.add(new Product("p1", ProductStatus.SELLABLE, "fc5", 30L, 3000.0));
        initialProducts.add(new Product("p5", ProductStatus.UNFULFILLABLE, "fc2", 7L, 980.0));
        initialProducts.add(new Product("p7", ProductStatus.UNFULFILLABLE, "fc3", 4L, 640.0));
        initialProducts.add(new Product("p8", ProductStatus.INBOUND, "fc1", 2L, 340.0));
        initialProducts.add(new Product("p9", ProductStatus.INBOUND, "fc5", 17L, 3060.0));
        initialProducts.add(new Product("p1", ProductStatus.SELLABLE, "fc4", 28L, 5320.0));
        initialProducts.add(new Product("p9", ProductStatus.SELLABLE, "fc2", 10L, 1800.0));
        initialProducts.add(new Product("p6", ProductStatus.UNFULFILLABLE, "fc3", 9L, 1350.0));
        initialProducts.add(new Product("p9", ProductStatus.INBOUND, "fc1", 23L, 4140.0));
        initialProducts.add(new Product("p2", ProductStatus.INBOUND, "fc4", 21L, 2310.0));
        initialProducts.add(new Product("p4", ProductStatus.UNFULFILLABLE, "fc2", 19L, 2470.0));
        initialProducts.add(new Product("p3", ProductStatus.SELLABLE, "fc5", 8L, 960.0));
        initialProducts.add(new Product("p2", ProductStatus.SELLABLE, "fc2", 18L, 1980.0));
        initialProducts.add(new Product("p5", ProductStatus.UNFULFILLABLE, "fc3", 13L, 1820.0));
        initialProducts.add(new Product("p7", ProductStatus.INBOUND, "fc1", 7L, 1120.0));
        initialProducts.add(new Product("p9", ProductStatus.SELLABLE, "fc4", 15L, 2700.0));
        initialProducts.add(new Product("p1", ProductStatus.INBOUND, "fc5", 6L, 600.0));
        initialProducts.add(new Product("p5", ProductStatus.SELLABLE, "fc2", 2L, 280.0));
        initialProducts.add(new Product("p6", ProductStatus.UNFULFILLABLE, "fc1", 6L, 900.0));
    }

    @BeforeEach
    public void createData() {
        initialProducts.forEach(product -> productRepository.save(product));
    }

    @AfterEach
    void removeData() {
        productRepository.deleteAll();
    }

    @Test
    public void findAllByStatusInboundSuccess() {
        List<Product> inboundProducts = productRepository.findAllByStatus(ProductStatus.INBOUND);
        for (Product product : inboundProducts) {
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