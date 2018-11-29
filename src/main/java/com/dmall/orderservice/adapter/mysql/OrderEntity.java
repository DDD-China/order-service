package com.dmall.orderservice.adapter.mysql;

import com.dmall.orderservice.domain.model.order.Order;
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
    private int quantity;
    private BigDecimal totalPrice;
    private String address;
    private String phoneNumber;
    private boolean paid;

    OrderEntity(Order order) {
        id = order.getId();
        productId = order.getProductId();
        quantity = order.getQuantity();
        totalPrice = order.getTotalPrice();
        address = order.getAddress();
        phoneNumber = order.getPhoneNumber();
        paid = order.isPaid();
    }

    Order toOrder() {
        return new Order(id, productId, quantity, totalPrice, address, phoneNumber, paid);
    }
}
