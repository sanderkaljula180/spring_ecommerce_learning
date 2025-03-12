package com.example.demo.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private List<OrderItemDTO> orderItems;


    public OrderDTO() {
    }

    public OrderDTO(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
