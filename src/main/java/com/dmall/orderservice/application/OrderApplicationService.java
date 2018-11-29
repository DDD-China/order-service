package com.dmall.orderservice.application;

import com.dmall.orderservice.domain.exception.NotFoundException;
import com.dmall.orderservice.domain.model.inventorylock.InventoryLock;
import com.dmall.orderservice.domain.model.inventorylock.InventoryLockRepository;
import com.dmall.orderservice.domain.model.order.Order;
import com.dmall.orderservice.domain.model.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OrderApplicationService {
    private final OrderRepository orderRepository;
    private final InventoryLockRepository inventoryLockRepository;

    @Autowired
    public OrderApplicationService(OrderRepository orderRepository, InventoryLockRepository InventoryLockRepository) {
        this.orderRepository = orderRepository;
        this.inventoryLockRepository = InventoryLockRepository;
    }

    public Order createOrder(long productId, int quantity, BigDecimal totalPrice, String address, String phoneNumber) {
        final String lockId = inventoryLockRepository.save(new InventoryLock(productId, quantity));

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
        orderRepository.save(order);
    }
}
