package com.dmall.orderservice.adapter.mysql;

import com.dmall.orderservice.domain.model.Order;
import com.dmall.orderservice.domain.model.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MysqlOrderRepository implements OrderRepository {
    private final OrderPersistence orderPersistence;

    @Autowired
    public MysqlOrderRepository(OrderPersistence orderPersistence) {
        this.orderPersistence = orderPersistence;
    }

    public Order save(Order order) {
        final OrderEntity orderEntity = new OrderEntity(order);
        orderPersistence.save(orderEntity);
        return order;
    }

    @Override
    public Optional<Order> findById(String orderId) {
        return orderPersistence.findById(orderId).map(OrderEntity::toOrder);
    }

}
