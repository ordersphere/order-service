package com.ordersphere.domain;

public enum OrderStatus {
    CREATED,
    INVENTORY_PROCESSED,
    PAYMENT_PENDING,
    PAYMENT_COMPLETED,
    FAILED,
    CANCELLED
}