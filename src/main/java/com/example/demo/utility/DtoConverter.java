package com.example.demo.utility;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.dto.OrderDTO;
import com.example.demo.model.dto.OrderItemDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoConverter {

    public OrderItemDTO orderItemToDto(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getQuantity()
        );
    }
}
