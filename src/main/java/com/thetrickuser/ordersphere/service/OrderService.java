package com.thetrickuser.ordersphere.service;

import com.thetrickuser.ordersphere.domain.OrderRequestDTO;
import com.thetrickuser.ordersphere.domain.OrderResponseDTO;
import com.thetrickuser.ordersphere.model.Order;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO request);
    OrderResponseDTO cancelOrder(OrderRequestDTO request);

}
