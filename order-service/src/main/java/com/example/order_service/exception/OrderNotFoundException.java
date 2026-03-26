package com.example.order_service.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("La orden con ID " + id + " no existe");
    }
}