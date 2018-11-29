package com.dmall.orderservice.application;

import com.dmall.orderservice.domain.exception.NotFoundException;
import com.dmall.orderservice.domain.model.inventorylock.InventoryLock;
import com.dmall.orderservice.domain.model.inventorylock.InventoryLockService;
import com.dmall.orderservice.domain.model.order.Order;
import com.dmall.orderservice.domain.model.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OrderApplicationService {
    private final OrderRepository orderRepository;
    private final InventoryLockService inventoryLockService;

    @Autowired
    public OrderApplicationService(OrderRepository orderRepository, InventoryLockService inventoryLockService) {
        this.orderRepository = orderRepository;
        this.inventoryLockService = inventoryLockService;
    }

    public Order createOrder(long productId, int quantity, BigDecimal totalPrice, String address, String phoneNumber) {
        final String lockId = inventoryLockService.createLock(new InventoryLock(productId, quantity));

        final Order order = new Order(productId, quantity, totalPrice, address, phoneNumber, lockId);
        orderRepository.save(order);
        return order;
    }

    public Optional<Order> getOrder(String orderId) {
        return orderRepository.findById(orderId);
    }

    public void paid(String orderId) {
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(Order.class, orderId));
        order.paid();

        inventoryLockService.finish(order.getLockId());
        orderRepository.save(order);
    }
}
