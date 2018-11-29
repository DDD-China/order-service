package com.dmall.orderservice.domain.model;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(String orderId);
}
