package com.dmall.orderservice.domain.model.inventorylock;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${dmall.inventory.url}", name = "inventory-service")
public interface InventoryLockRepository {

    @PostMapping("/inventories/lock")
    String save(InventoryLock request);
}

