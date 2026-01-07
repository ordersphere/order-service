package com.ordersphere.service.impl;

import com.ordersphere.domain.OrderRequestDTO;
import com.ordersphere.domain.OrderResponseDTO;
import com.ordersphere.model.Order;
import com.ordersphere.repository.OrderRepository;
import com.ordersphere.service.OrderService;
import com.ordersphere.util.OrderMapper;
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
