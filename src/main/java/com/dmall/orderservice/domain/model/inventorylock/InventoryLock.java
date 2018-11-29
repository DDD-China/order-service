package com.dmall.orderservice.domain.model.inventorylock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InventoryLock {
    private final long productId;
    private final int quantity;
}
