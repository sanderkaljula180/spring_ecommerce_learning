package com.example.demo.Service;

import com.example.demo.model.Order;
import com.example.demo.model.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    /**
     * Places a new order, handling stock deduction and validation.
     *
     * @param orderDTO The order to place.
     * @return The placed order.
     */
    OrderDTO placeOrder(OrderDTO orderDTO);

    /**
     * Retrieves order placed by existing user ID
     *
     * @param userId The user ID
     * @return A list of users orders
     */
    List<Order> getOrdersByUserId(Long userId);

    // Remove order
}
