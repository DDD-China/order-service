package com.dmall.orderservice.adapter.mysql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "orders")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class OrderEntity {
    @Id
    private String id;
    private long productId;
    private BigDecimal totalPrice;
}
