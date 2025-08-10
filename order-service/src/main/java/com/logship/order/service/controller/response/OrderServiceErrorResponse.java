package com.logship.order.service.controller.response;

public class OrderServiceErrorResponse {
    public final String code;
    public final String message;
    public OrderServiceErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
