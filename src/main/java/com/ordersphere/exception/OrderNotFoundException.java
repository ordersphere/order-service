package com.ordersphere.exception;

import com.ordersphere.core.exception.BaseException;
import com.ordersphere.domain.error.OrderErrorCode;

public class OrderNotFoundException extends BaseException {
    public OrderNotFoundException() {
        super("Order not found", OrderErrorCode.ORDER_NOT_FOUND.getCode());
    }
}
