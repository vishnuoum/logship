package com.logship.shipment.service.exception;

import org.springframework.http.HttpStatus;

public class ShipmentServiceException extends RuntimeException {
    public final HttpStatus status;
    public final String code;
    public final String message;
    public ShipmentServiceException(String code, String message, HttpStatus status) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
