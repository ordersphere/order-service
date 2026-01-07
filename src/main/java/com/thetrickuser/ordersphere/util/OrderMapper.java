package com.thetrickuser.ordersphere.util;

import com.thetrickuser.ordersphere.domain.OrderRequestDTO;
import com.thetrickuser.ordersphere.domain.OrderResponseDTO;
import com.thetrickuser.ordersphere.domain.OrderStatus;
import com.thetrickuser.ordersphere.model.Order;

public class OrderMapper {

    public static Order toOrderEntity(OrderRequestDTO requestDTO) {
        Order order = new Order();
        order.setCustomerId(requestDTO.customerId());
        order.setTotalAmount(requestDTO.totalAmount());
        order.setStatus(OrderStatus.CREATED);

        return order;
    }

    public static OrderResponseDTO toOrderResponse(Order order) {
        return new OrderResponseDTO(order.getId(), order.getCustomerId(), order.getTotalAmount(), order.getStatus().name());
    }
}
