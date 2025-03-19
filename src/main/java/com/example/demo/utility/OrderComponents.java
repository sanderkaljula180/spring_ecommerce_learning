package com.example.demo.utility;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.model.dto.OrderItemDTO;
import com.example.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderComponents {

    private final ProductRepository productRepository;

    public OrderComponents(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public OrderItem processOrderItem(OrderItemDTO orderItemDTO, Order order) {

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
    }
}
