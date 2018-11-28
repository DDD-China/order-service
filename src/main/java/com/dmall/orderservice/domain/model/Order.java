package com.dmall.orderservice.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Order {
    private final String orderId;
    private final String productId;
}
