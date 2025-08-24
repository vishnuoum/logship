package com.logship.shipment.service.controller.request.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderDetails {

    @NotEmpty(message = "Address cannot be null or empty")
    private String address;
    @NotNull(message = "Order ID cannot be null")
    private UUID orderId;
    @NotEmpty(message = "Pin Code cannot be null or empty")
    private String pinCode;
}
