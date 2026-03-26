package com.example.order_service.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateOrderStatusRequest {
    private String status;

}