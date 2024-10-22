package com.example.demo.Service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
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


            // Set the product in the order item
        }


        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return List.of();
    }
}
