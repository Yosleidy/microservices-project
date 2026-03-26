package com.example.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Data
@AllArgsConstructor
public class OrderItemDto {
    private Long id;
    private Long productId;
    private int quantity;
    private double price;
}