package com.logship.shipment.service.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeliveryRequest {

    @NotNull(message = "Order ID cannot be null")
    private UUID orderId;
}
