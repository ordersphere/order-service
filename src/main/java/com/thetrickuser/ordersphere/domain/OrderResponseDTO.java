package com.thetrickuser.ordersphere.domain;

import java.math.BigDecimal;

public record OrderResponseDTO(Long orderId, Long customerId, BigDecimal totalAmount, String status) {
}
