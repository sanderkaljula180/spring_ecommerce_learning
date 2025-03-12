package com.example.demo.Service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.model.dto.OrderDTO;
import com.example.demo.model.dto.OrderItemDTO;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderDTO placeOrder(OrderDTO orderDTO) {
        Order order = new Order();

        List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                .map(orderItemDTO -> {
                   OrderItem orderItem = new OrderItem();
                   Product product = productRepository.findById(orderItemDTO.getId())
                           .orElseThrow(() -> new EntityNotFoundException("Product not found"));

                   product.setQuantity(Math.max(0, product.getQuantity() - orderItemDTO.getQuantity()));
                   if (product.getQuantity() < 0) {
                       throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
                   }
                   productRepository.save(product);

                   orderItem.setProduct(product);
                   orderItem.setQuantity(orderItemDTO.getQuantity());
                   orderItem.setPrice(product.getPrice().multiply(new BigDecimal(orderItemDTO.getQuantity())));
                   orderItem.setOrder(order);
                return orderItem;
                }).toList();

        order.setOrderDate(LocalDateTime.now());
        order.setOrderItems(orderItems);

        // NOW WE SHOULD CREATE SECURITY BECAUSE THIS
        //order.setUser(User JWT token or something);

        //order.setOrderDate(LocalDateTime.now());

        //return orderRepository.save(order);
        return null;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return List.of();
    }
}
