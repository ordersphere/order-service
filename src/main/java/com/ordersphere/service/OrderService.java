package com.ordersphere.service;

import com.ordersphere.domain.OrderRequestDTO;
import com.ordersphere.domain.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO request);
    OrderResponseDTO cancelOrder(OrderRequestDTO request);
    OrderResponseDTO getOrder(long orderId);
    List<OrderResponseDTO> getAllOrders(long customerId);

}
