package com.logship.order.service.exception;

import org.springframework.http.HttpStatus;

public class OrderServiceException extends RuntimeException {
    public final HttpStatus status;
    public final String code;
    public final String message;
    public OrderServiceException(String code, String message, HttpStatus status) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
