package com.ordersphere.exception;

import com.ordersphere.core.exception.BaseException;
import com.ordersphere.domain.error.OrderErrorCode;

public class OrderNotFoundException extends BaseException {
    public OrderNotFoundException(Long orderId) {
        super("Order not found with id:" + orderId, OrderErrorCode.ORDER_NOT_FOUND.getCode());
    }
}
