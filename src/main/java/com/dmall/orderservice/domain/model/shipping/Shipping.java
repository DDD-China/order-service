package com.dmall.orderservice.domain.model.shipping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Shipping {
    private final int orderId;
    private final int quantity;
    private final String address;
}
