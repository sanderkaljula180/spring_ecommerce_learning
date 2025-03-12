package com.example.demo.controller;

import com.example.demo.Service.OrderServiceImpl;
import com.example.demo.model.Order;
import com.example.demo.model.dto.OrderDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add-order")
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody @Valid OrderDTO orderDTO) {
        logger.info("Received request to place order: {}", orderDTO);
        OrderDTO addedOrder = orderService.placeOrder(orderDTO);
        return new ResponseEntity<>(addedOrder, HttpStatus.CREATED);
    }
}
