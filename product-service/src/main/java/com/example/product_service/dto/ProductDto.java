package com.example.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor  // Crea el constructor vacío
@AllArgsConstructor  // Crea el constructor con todos los campos

public class ProductDto {

    private Long id;
    private String name;
    private double price;



}