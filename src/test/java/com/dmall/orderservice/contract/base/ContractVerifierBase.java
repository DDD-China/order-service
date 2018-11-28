package com.dmall.orderservice.contract.base;

import com.dmall.orderservice.adapter.apis.OrderController;
import com.dmall.orderservice.application.OrderApplicationService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class ContractVerifierBase {
    @Before
    public void setup() {
        final OrderApplicationService orderApplicationService = mock(OrderApplicationService.class);
        RestAssuredMockMvc.standaloneSetup(new OrderController(orderApplicationService));
    }
}
