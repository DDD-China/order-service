package com.dmall.orderservice.application;

import com.dmall.orderservice.domain.model.order.Order;
import com.dmall.orderservice.domain.model.order.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;

@Service
public class OrderApplicationService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @Autowired
    public OrderApplicationService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    public Order createOrder(long productId, int quantity, BigDecimal totalPrice, String address, String phoneNumber) {
//lock inventory
        inventoryClient.lock(new Lock(quantity,productId));

        final Order order = new Order(productId, quantity, totalPrice, address, phoneNumber);
        orderRepository.save(order);
        return order;
    }

    @FeignClient(url = "${dmall.inventory.url}", name = "inventory")
    public interface InventoryClient {
        @RequestMapping(method = RequestMethod.POST, value = "/inventories/lock")
        String lock(Lock lock);
    }

    @Getter
    @AllArgsConstructor
    public static class Lock {
        int quantity;
        long productId;
    }

    public Order getOrder(String orderId) {
        return orderRepository.getOrder(orderId);
    }
}
