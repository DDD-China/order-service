package com.dmall.orderservice.adapter.apis;


import com.dmall.orderservice.application.OrderApplicationService;
import com.dmall.orderservice.domain.model.Order;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
    public void createOrder(CreateOrderRequest request) {

    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable("orderId") String orderId) {
        return new Order(orderId, "product-id-1");
    }

    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Order updateOrder(@PathVariable String orderId) {
        return new Order(orderId, "product-id-9");
    }

    @Setter
    private static class CreateOrderRequest {
        private String productId;
        private int quantity;
        private String address;
        private BigDecimal totalPrice;
        private String phoneNumber;
    }
}
