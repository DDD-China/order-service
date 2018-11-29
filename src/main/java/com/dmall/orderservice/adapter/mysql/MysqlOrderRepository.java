package com.dmall.orderservice.adapter.mysql;

import com.dmall.orderservice.domain.model.Order;
import com.dmall.orderservice.domain.model.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlOrderRepository implements OrderRepository {
    private final OrderPersistence orderPersistence;

    @Autowired
    public MysqlOrderRepository(OrderPersistence orderPersistence) {
        this.orderPersistence = orderPersistence;
    }

    public Order save(Order order) {
        final OrderEntity orderEntity = OrderEntity.builder()
                .id(order.getId())
                .productId(order.getProductId())
                .totalPrice(order.getTotalPrice())
                .build();
        orderPersistence.save(orderEntity);
        return order;
    }

}
