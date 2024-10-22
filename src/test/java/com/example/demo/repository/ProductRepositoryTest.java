package com.example.demo.repository;

import com.example.demo.model.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Test findByNameContaining")
    public void testFindByNameContaining() {
        // Arrange
        Product product1 = new Product();
        product1.setName("Laptop Pro");
        product1.setDescription("A powerful laptop");
        product1.setPrice(BigDecimal.valueOf(1500));
        product1.setQuantity(10);

        Product product2 = new Product();
        product2.setName("Laptop Air");
        product2.setDescription("A lightweight laptop");
        product2.setPrice(BigDecimal.valueOf(1000));
        product2.setQuantity(15);

        productRepository.save(product1);
        productRepository.save(product2);

        // Act
        List<Product> products = productRepository.findByNameContaining("Laptop");

        // Assert
        assertEquals(2, products.size());
    }
}
