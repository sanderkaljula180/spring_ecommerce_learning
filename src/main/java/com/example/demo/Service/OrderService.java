package com.example.demo.Service;

import com.example.demo.model.Order;
import com.example.demo.model.Product;

import java.util.List;

public interface OrderService {

    /**
     * Places a new order, handling stock deduction and validation.
     *
     * @param order The order to place.
     * @return The placed order.
     */
    Order placeOrder(Order order);

    /**
     * Retrieves order placed by existing user ID
     *
     * @param userId The user ID
     * @return A list of users orders
     */
    List<Order> getOrdersByUserId(Long userId);
}
