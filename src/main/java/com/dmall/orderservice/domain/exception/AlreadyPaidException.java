package com.dmall.orderservice.domain.exception;

public class AlreadyPaidException extends RuntimeException {
    public AlreadyPaidException(String orderId) {
        super(orderId);
    }
}
