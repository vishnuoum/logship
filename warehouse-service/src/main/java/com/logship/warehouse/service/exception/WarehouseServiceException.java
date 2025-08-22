package com.logship.warehouse.service.exception;

import org.springframework.http.HttpStatus;

public class WarehouseServiceException extends RuntimeException {
    public final HttpStatus status;
    public final String code;
    public final String message;
    public WarehouseServiceException(String code, String message, HttpStatus status) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
