package com.example.demo.repository;

import com.example.demo.model.Order;
import com.example.demo.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Test testFindOrderByUserId")
    public void testFindOrderByUserId() {
        // Arrange
        User user = new User();
        user.setUsername("dave");
        user.setPassword("password");
        user.setEmail("dave@example.com");
        userRepository.save(user);

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);

        // Act
        List<Order> orders = orderRepository.findByUserId(user.getId());

        // Assert
        assertEquals(1, orders.size());
        assertEquals(user.getId(), orders.get(0).getUser().getId());
    }
}
