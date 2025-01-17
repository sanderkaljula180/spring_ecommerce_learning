package com.example.demo.Service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order placeOrder(Order order) {
        for (OrderItem item : order.getOrderItems()) {
            // Retrieve the product to update stock
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            // Deduct the ordered quantity from stock
            int updateQuantity = product.getQuantity() - item.getQuantity();
            if (updateQuantity < 0) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }

            // Update the product in the repository
            product.setQuantity(updateQuantity);
            productRepository.save(product);

            // Set the product in the order item. Also add dateTime and get user
            item.setOrder(order);

        }

        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return List.of();
    }
}
