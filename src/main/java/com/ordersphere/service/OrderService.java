package com.ordersphere.service;

import com.ordersphere.domain.OrderRequestDTO;
import com.ordersphere.domain.OrderResponseDTO;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO request);
    OrderResponseDTO cancelOrder(OrderRequestDTO request);

}
