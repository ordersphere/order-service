package com.ordersphere.controller;

import com.ordersphere.domain.OrderRequestDTO;
import com.ordersphere.domain.OrderResponseDTO;
import com.ordersphere.domain.OrderSummaryDTO;
import com.ordersphere.service.OrderQueryService;
import com.ordersphere.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final OrderQueryService orderQueryService;

    public OrderController(OrderService orderService, OrderQueryService orderQueryService) {
        this.orderService = orderService;
        this.orderQueryService = orderQueryService;
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO request) {
        log.info("Received order creation request for customerId: {}", request.customerId());
        OrderResponseDTO response = orderService.createOrder(request);
        return ResponseEntity.created(URI.create("/orders/" + response.orderId())).body(response);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long orderId) {
        log.info("Received request to fetch order with ID: {}", orderId);
        OrderResponseDTO order = orderService.getOrder(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<Page<OrderSummaryDTO>> getOrdersForCustomer(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Received request to fetch orders for customerId: {}", customerId);
        Page<OrderSummaryDTO> orders = orderQueryService.getOrdersForCustomer(customerId, page, size);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/orders/{orderId}/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Long orderId) {
        log.info("Received request to cancel order with ID: {}", orderId);
        OrderResponseDTO response = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(response);
    }
}
