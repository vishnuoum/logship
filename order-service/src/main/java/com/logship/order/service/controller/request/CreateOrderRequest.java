package com.logship.order.service.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {

    @NotEmpty(message = "Pickup address can be empty")
    private String pickupAddress;
    @NotEmpty(message = "Drop address can be empty")
    private String dropAddress;
    private boolean isFragile;
    private String remarks;
}
