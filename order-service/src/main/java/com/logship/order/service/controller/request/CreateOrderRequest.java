package com.logship.order.service.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {

    @NotEmpty(message = "Pickup address can be empty")
    private String pickupAddress;
    @NotEmpty(message = "Pickup pinCode can be empty")
    @Size(min = 6, max = 6, message = "Pickup pinCode must be of length 6")
    private String pickupPinCode;
    @NotEmpty(message = "Drop address can be empty")
    private String dropAddress;
    @NotEmpty(message = "Drop pinCode can be empty")
    @Size(min = 6, max = 6, message = "Pickup pinCode must be of length 6")
    private String dropPinCode;
    private boolean isFragile;
    private String remarks;
}
