package com.dmall.orderservice.application;

import com.dmall.orderservice.domain.model.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderApplicationService {
    public Order createOrder(String productId, int quantity, BigDecimal totalPrice, String address, String phoneNumber) {
        return new Order(productId, quantity, totalPrice, address, phoneNumber);
    }
}
