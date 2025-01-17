package com.example.demo.service;

import com.example.demo.Service.OrderServiceImpl;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Test
    void whenOrderIsPlaced_success() {
        // Arrange
        User user = new User();
        user.setUsername("john_doe");

        Product product1 = new Product();
        product1.setName("Lenovo");
        product1.setPrice(BigDecimal.valueOf(100));
        product1.setQuantity(10);

        Product product2 = new Product();
        product1.setId();
        product1.setPrice(BigDecimal.valueOf(200));
        product1.setQuantity(10);

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);

        OrderItem item1 = new OrderItem();
        item1.setOrder(order);
        item1.setProduct(product1);
        item1.setPrice(product1.getPrice());
        item1.setQuantity(1);

        OrderItem item2 = new OrderItem();
        item2.setOrder(order);
        item2.setProduct(product2);
        item2.setPrice(product2.getPrice());
        item2.setQuantity(2);

        order.setOrderItems(List.of(item1, item2));




        when()



        // Act

        // Assert
    }
}
