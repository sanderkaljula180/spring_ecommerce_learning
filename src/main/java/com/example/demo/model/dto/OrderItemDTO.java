package com.example.demo.model.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private int quantity;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
