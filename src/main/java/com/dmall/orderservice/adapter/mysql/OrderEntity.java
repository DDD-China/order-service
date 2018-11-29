package com.dmall.orderservice.adapter.mysql;

import com.dmall.orderservice.domain.model.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "orders")
@Getter
@NoArgsConstructor
class OrderEntity {
    @Id
    private String id;
    private long productId;
    private BigDecimal totalPrice;

    OrderEntity(Order order) {
        this.id = order.getId();
        this.productId = order.getProductId();
        this.totalPrice = order.getTotalPrice();
    }

    Order toOrder() {
        return new Order(id, productId, 0, totalPrice, "address", "phoneNumber", false);
    }
}
