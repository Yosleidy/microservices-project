package com.example.order_service.service;

import com.example.order_service.client.ProductClient;
import com.example.order_service.client.UserClient;
import com.example.order_service.dto.*;
import com.example.order_service.entity.OrderItem;
import com.example.order_service.entity.Orders;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.exception.ProductNotFoundException;
import com.example.order_service.exception.UserNotFoundException;
import com.example.order_service.repository.OrderRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private ProductClient productClient;



    @Autowired
    private OrderRepository orderRepository;
    public OrderDto createOrder(OrderRequest request) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new RuntimeException("Items cannot be null or empty");
        }

    UserDto user = userClient.getUserById(request.getUserId());


        Orders order = new Orders();
        order.setUserId(request.getUserId());
        order.setStatus("CREATED");
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (OrderItemRequest itemReq : request.getItems()) {
            ProductDto product = productClient.getProductById(itemReq.getProductId());
            if (product == null) throw new ProductNotFoundException(itemReq.getProductId());

            OrderItem item = new OrderItem();
            item.setProductId(itemReq.getProductId());
            item.setQuantity(itemReq.getQuantity());
            item.setPrice(product.getPrice());
            item.setOrder(order);
            items.add(item);

            total += product.getPrice() * itemReq.getQuantity();
        }

        order.setTotal(total);
        order.setItems(items);

        Orders savedOrder = orderRepository.save(order);

        // map a DTO
        List<OrderItemDto> itemDtos = savedOrder.getItems().stream()
                .map(i -> new OrderItemDto(i.getId(), i.getProductId(), i.getQuantity(), i.getPrice()))
                .toList();

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(savedOrder.getId());
        orderDto.setUserId(savedOrder.getUserId());
        orderDto.setTotal(savedOrder.getTotal());
        orderDto.setStatus(savedOrder.getStatus());
        orderDto.setCreatedAt(savedOrder.getCreatedAt());
        orderDto.setItems(itemDtos);

        return orderDto;
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderDto getOrderById(Long id) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        // Mapeo de los items a DTO
        List<OrderItemDto> itemDtos = order.getItems().stream()
                .map(i -> new OrderItemDto(i.getId(), i.getProductId(), i.getQuantity(), i.getPrice()))
                .toList();

           return new OrderDto(
                order.getId(),
                order.getUserId(),
                order.getTotal(),
                order.getStatus(),
                order.getCreatedAt(),
                itemDtos
        );
    }
    public String deleteOrder(Long id) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        orderRepository.delete(order);
        return "La orden con ID " + id + " ha sido eliminada correctamente";
    }

    public Orders updateOrderStatus(Long id, String status) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        order.setStatus(status);

        return orderRepository.save(order);
    }
}