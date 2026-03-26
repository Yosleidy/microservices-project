package com.example.order_service;

import com.example.order_service.client.ProductClient;
import com.example.order_service.client.UserClient;
import com.example.order_service.dto.*;
import com.example.order_service.entity.Orders;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private UserClient userClient;

	@Mock
	private ProductClient productClient;

	@InjectMocks
	private OrderService orderService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void createOrder_ShouldReturnSavedOrder() {
		// Mock del usuario
		Long userId = 1L;
		UserDto mockUser = new UserDto();
		mockUser.setId(userId);
		mockUser.setName("Jorge");
		mockUser.setEmail("jorge@example.com");
		when(userClient.getUserById(userId)).thenReturn(mockUser);

		// Mock de productos
		ProductDto product1 = new ProductDto();
		product1.setId(1L);
		product1.setPrice(1200.0);

		ProductDto product2 = new ProductDto();
		product2.setId(2L);
		product2.setPrice(1200.0);

		when(productClient.getProductById(1L)).thenReturn(product1);
		when(productClient.getProductById(2L)).thenReturn(product2);

		// Crear OrderRequest
		List<OrderItemRequest> items = Arrays.asList(
				new OrderItemRequest(1L, 2),
				new OrderItemRequest(2L, 1)
		);
		OrderRequest request = new OrderRequest();
		request.setUserId(userId);
		request.setItems(items);

		// Mock del repository
		when(orderRepository.save(any(Orders.class))).thenAnswer(invocation -> invocation.getArgument(0));

		// Ejecutar servicio
		OrderDto savedOrder = orderService.createOrder(request);

		// Validar resultados
		assertEquals(userId, savedOrder.getUserId());
		assertEquals(3600.0, savedOrder.getTotal()); // 1200*2 + 1200*1
		assertEquals(2, savedOrder.getItems().size());

		// Verificar llamadas
		verify(userClient, times(1)).getUserById(userId);
		verify(productClient, times(1)).getProductById(1L);
		verify(productClient, times(1)).getProductById(2L);
		verify(orderRepository, times(1)).save(any(Orders.class));
	}
}