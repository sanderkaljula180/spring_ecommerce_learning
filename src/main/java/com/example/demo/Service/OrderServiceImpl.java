package com.example.demo.Service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.dto.OrderDTO;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.utility.OrderComponents;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderComponents orderComponents;

    public OrderServiceImpl(OrderComponents orderComponents) {
        this.orderComponents = orderComponents;
    }

    @Override
    public OrderDTO placeOrder(OrderDTO orderDTO) {
        Order order = new Order();

        List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                .map(orderItemDTO -> orderComponents.processOrderItem(orderItemDTO, order)).toList();

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
