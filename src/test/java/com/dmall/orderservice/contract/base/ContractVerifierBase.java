package com.dmall.orderservice.contract.base;

import com.dmall.orderservice.adapter.apis.OrderController;
import com.dmall.orderservice.application.OrderApplicationService;
import com.dmall.orderservice.domain.model.Order;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContractVerifierBase {
    @Before
    public void setup() {
        final OrderApplicationService orderApplicationService = mock(OrderApplicationService.class);
        when(orderApplicationService.createOrder(anyString(), anyInt(), any(BigDecimal.class), anyString(), anyString()))
                .thenReturn(
                        new Order("order-id-1", "product-id-1", 10, new BigDecimal("1"), "address", "phoneNumber", false)
                );
        RestAssuredMockMvc.standaloneSetup(new OrderController(orderApplicationService));
    }
}
