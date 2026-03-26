package com.example.order_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
@Getter
@Setter
public class OrderItemRequest {

    private Long productId;  // ID del producto
    private int quantity;    // Cantidad pedida
    public OrderItemRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}