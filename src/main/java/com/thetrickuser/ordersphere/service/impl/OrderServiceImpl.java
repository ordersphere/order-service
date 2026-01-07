package com.thetrickuser.ordersphere.service.impl;

import com.thetrickuser.ordersphere.domain.OrderRequestDTO;
import com.thetrickuser.ordersphere.domain.OrderResponseDTO;
import com.thetrickuser.ordersphere.model.Order;
import com.thetrickuser.ordersphere.repository.OrderRepository;
import com.thetrickuser.ordersphere.service.OrderService;
import com.thetrickuser.ordersphere.util.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        Order orderEntity = OrderMapper.toOrderEntity(request);
        Order saved = orderRepository.save(orderEntity);
        return OrderMapper.toOrderResponse(saved);
    }

    @Override
    public OrderResponseDTO cancelOrder(OrderRequestDTO request) {
        return null;
    }
}
