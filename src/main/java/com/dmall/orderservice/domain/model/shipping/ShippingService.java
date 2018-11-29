package com.dmall.orderservice.domain.model.shipping;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${dmall.shipping.url}", name = "shipping-service")
public interface ShippingService {
    @PostMapping("/shippings")
    void createShipping(Shipping shipping);
}
