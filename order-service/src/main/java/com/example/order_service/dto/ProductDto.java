
package com.example.order_service.dto;



import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor

public class ProductDto {

    private Long id;
    private String name;
    private Double price;


}