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
    private final ShippingClient shippingClient;
    
    @Autowired
    public OrderApplicationService(OrderRepository orderRepository, InventoryClient inventoryClient, ShippingClient shippingClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
        this.shippingClient = shippingClient;
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
     public void paidOrder(String orderId){
         final Order order = orderRepository.getOrder(orderId);
         order.paid();
         shippingClient.shipping(new Shipping(order.getId(),order.getQuantity(),order.getAddress()));
         orderRepository.save(order);
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

    @FeignClient(url = "${dmall.shipping.url}", name = "shipping")
    public interface ShippingClient {
        @RequestMapping(method = RequestMethod.POST, value = "/shippings")
        void shipping(Shipping shipping);
    }
    
    @Getter
    @AllArgsConstructor
    public static class Shipping{
        String orderId;
        int quantity;
        String address;
    }
    
}
