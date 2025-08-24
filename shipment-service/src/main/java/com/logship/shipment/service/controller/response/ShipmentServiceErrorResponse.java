package com.logship.shipment.service.controller.response;

public class ShipmentServiceErrorResponse {
    public final String code;
    public final String message;
    public ShipmentServiceErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
