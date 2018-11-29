package com.dmall.orderservice.contract.base;

import com.dmall.orderservice.adapter.apis.OrderController;
import com.dmall.orderservice.application.OrderApplicationService;
import com.dmall.orderservice.domain.model.order.Order;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderBase {
    @Before
    public void setup() {
        final OrderApplicationService orderApplicationService = mock(OrderApplicationService.class);
        when(orderApplicationService.createOrder(anyLong(), anyInt(), any(BigDecimal.class), anyString(), anyString()))
                .thenReturn(
                        new Order("order-id-1", 1, 10, new BigDecimal("1"), "address", "phoneNumber", false)
                );
        when(orderApplicationService.getOrder("8888"))
                .thenReturn(Optional.of(new Order("8888", 1, 1, new BigDecimal("1"), "address", "110", false)));
        RestAssuredMockMvc.standaloneSetup(new OrderController(orderApplicationService));
    }
}
