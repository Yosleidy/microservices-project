
package com.example.order_service.exception;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleOrderNotFound(OrderNotFoundException ex) {
        return ResponseEntity.status(404)
                .body(Map.of("error", ex.getMessage()));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUser(UserNotFoundException ex) {
        return ResponseEntity.status(404).body(Map.of(
                "status", 404,
                "error", "Not Found",
                "message", ex.getMessage(),
                "timestamp", LocalDateTime.now()
        ));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProduct(ProductNotFoundException ex) {
        return ResponseEntity.status(404).body(Map.of(
                "status", 404,
                "error", "Not Found",
                "message", ex.getMessage(),
                "timestamp", LocalDateTime.now()
        ));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeign(FeignException ex) {
        return ResponseEntity.status(500).body(Map.of(
                "status", 500,
                "error", "Microservice Error",
                "message", "Error communicating with external service",
                "details", ex.getMessage(),
                "timestamp", LocalDateTime.now()
        ));
    }
}

