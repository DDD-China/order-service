package com.dmall.orderservice.adapter.apis;


import com.dmall.orderservice.application.OrderApplicationService;
import com.dmall.orderservice.domain.model.Order;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderApplicationService orderApplicationService;

    @Autowired
    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return orderApplicationService.createOrder(request.productId, request.quantity, request.totalPrice, request.address, request.phoneNumber);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable("orderId") String orderId) {
        final Optional<Order> order = orderApplicationService.getOrder(orderId);
        return order.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrder(@PathVariable String orderId, @RequestBody UpdateOrderRequest requset) {
        if (requset.paid != null && requset.paid) {
            orderApplicationService.paid(orderId);
        }
    }

    @Setter
    private static class CreateOrderRequest {
        @NotNull
        private Long productId;
        @NotNull
        private Integer quantity;
        @NotNull
        private BigDecimal totalPrice;
        @NotNull
        private String address;
        @NotNull
        private String phoneNumber;
    }

    @Setter
    private static class UpdateOrderRequest {
        private Boolean paid;
    }
}
