package com.logship.shipment.service.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PickupRequest {

    @NotNull(message = "Order ID cannot be null")
    private UUID orderId;
}
