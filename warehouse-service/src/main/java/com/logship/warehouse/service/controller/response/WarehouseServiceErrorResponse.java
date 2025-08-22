package com.logship.warehouse.service.controller.response;

public class WarehouseServiceErrorResponse {
    public final String code;
    public final String message;
    public WarehouseServiceErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
