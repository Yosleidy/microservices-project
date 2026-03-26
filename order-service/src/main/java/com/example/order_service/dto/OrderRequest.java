
package com.example.order_service.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Data
@Getter
@Setter
public class OrderRequest {
    private Long userId;
    private List<OrderItemRequest> items; // lista de IDs de productos
}