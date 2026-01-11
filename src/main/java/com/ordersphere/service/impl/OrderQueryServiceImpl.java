package com.ordersphere.service.impl;

import com.ordersphere.domain.OrderSummaryDTO;
import com.ordersphere.repository.OrderRepository;
import com.ordersphere.service.OrderQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    private static final Logger log = LoggerFactory.getLogger(OrderQueryServiceImpl.class);
    private final OrderRepository orderRepository;

    public OrderQueryServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<OrderSummaryDTO> getOrdersForCustomer(Long customerId, int page, int size) {
        log.info("Fetching orders for customerId: {}, page: {}, size: {}", customerId, page, size);
        PageRequest pageRequest = PageRequest.of(page, size);

        return orderRepository.findByCustomerId(customerId, pageRequest)
                .map(order -> new OrderSummaryDTO(
                        order.getId(),
                        order.getTotalAmount(),
                        order.getStatus().name()
                ));
    }
}
