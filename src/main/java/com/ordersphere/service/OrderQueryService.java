package com.ordersphere.service;

import com.ordersphere.domain.OrderSummaryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderQueryService {

    Page<OrderSummaryDTO> getOrdersForCustomer(Long customerId, int page, int size);
}
