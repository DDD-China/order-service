package com.dmall.orderservice.domain.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Class<?> clazz, Object id) {
        super(clazz.getSimpleName() + " " + id.toString());
    }
}
