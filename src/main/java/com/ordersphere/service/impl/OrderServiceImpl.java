package com.ordersphere.service.impl;

import com.ordersphere.domain.OrderRequestDTO;
import com.ordersphere.domain.OrderResponseDTO;
import com.ordersphere.domain.OrderStatus;
import com.ordersphere.exception.OrderNotFoundException;
import com.ordersphere.model.Order;
import com.ordersphere.repository.OrderRepository;
import com.ordersphere.service.OrderService;
import com.ordersphere.util.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public OrderResponseDTO cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if (order.getStatus() == OrderStatus.CANCELLED) {
            return OrderMapper.toOrderResponse(order);
        }

        order.setStatus(OrderStatus.CANCELLED);
        Order saved = orderRepository.save(order);

        return OrderMapper.toOrderResponse(saved);
    }

    @Override
    public OrderResponseDTO getOrder(long orderId) {
        return orderRepository.findById(orderId)
                .map(OrderMapper::toOrderResponse)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}
