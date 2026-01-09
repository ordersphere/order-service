package com.ordersphere.domain;

import java.math.BigDecimal;

public record OrderSummaryDTO(Long orderId, BigDecimal totalAmount, String status) {
}
