package com.dmall.orderservice.domain.model.inventorylock;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(url = "${dmall.inventory.url}", name = "inventory-service")
public interface InventoryLockService {

    @PostMapping("/inventories/lock")
    String createLock(InventoryLock request);

    @PutMapping("/inventories/lock/{lockId}")
    void finish(@PathVariable String lockId);
}

