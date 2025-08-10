package com.logship.order.service.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateOrderRequest {

    @NotEmpty(message = "Pickup address can be empty")
    private String pickupAddress;
    @NotEmpty(message = "Drop address can be empty")
    private String dropAddress;
    @NotNull(message = "Customer ID cannot be null")
    private UUID customerId;
    private boolean isFragile;
    private String remarks;
}
