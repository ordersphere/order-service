package com.ordersphere.domain;

import java.math.BigDecimal;

public record OrderRequestDTO(Long customerId, BigDecimal totalAmount) {
}
