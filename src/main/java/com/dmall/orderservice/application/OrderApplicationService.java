package com.dmall.orderservice.application;

import com.dmall.orderservice.domain.model.Order;
import com.dmall.orderservice.domain.model.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OrderApplicationService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderApplicationService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(long productId, int quantity, BigDecimal totalPrice, String address, String phoneNumber) {
        final Order order = new Order(productId, quantity, totalPrice, address, phoneNumber);
        orderRepository.save(order);
        return order;
    }

    public Optional<Order> getOrder(String orderId) {
        return orderRepository.findById(orderId);
    }
}
