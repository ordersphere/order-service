package com.ordersphere.service.impl;

import com.ordersphere.domain.OrderRequestDTO;
import com.ordersphere.domain.OrderResponseDTO;
import com.ordersphere.domain.OrderStatus;
import com.ordersphere.exception.OrderNotFoundException;
import com.ordersphere.model.Order;
import com.ordersphere.repository.OrderRepository;
import com.ordersphere.service.OrderService;
import com.ordersphere.util.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        log.info("Creating order for customerId: {}", request.customerId());
        Order orderEntity = OrderMapper.toOrderEntity(request);
        Order saved = orderRepository.save(orderEntity);
        log.info("Order created successfully with order id: {}, status: {}", saved.getId(), saved.getStatus());
        return OrderMapper.toOrderResponse(saved);
    }

    @Override
    public OrderResponseDTO cancelOrder(Long orderId) {
        log.info("Cancelling order with id: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> getNotFoundException(orderId));

        if (order.getStatus() == OrderStatus.CANCELLED) {
            log.info("Order with id: {} is already cancelled", orderId);
            return OrderMapper.toOrderResponse(order);
        }

        order.setStatus(OrderStatus.CANCELLED);
        Order saved = orderRepository.save(order);
        log.info("Order with id: {} cancelled successfully", orderId);

        return OrderMapper.toOrderResponse(saved);
    }

    @Override
    public OrderResponseDTO getOrder(long orderId) {
        log.info("Fetching order with id: {}", orderId);
        return orderRepository.findById(orderId)
                .map(OrderMapper::toOrderResponse)
                .orElseThrow(() -> getNotFoundException(orderId));
    }

    private static OrderNotFoundException getNotFoundException(Long orderId) {
        log.info("Order with id: {} not found", orderId);
        return new OrderNotFoundException(orderId);
    }
}
