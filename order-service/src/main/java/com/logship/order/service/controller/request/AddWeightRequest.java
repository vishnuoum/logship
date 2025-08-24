package com.logship.order.service.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddWeightRequest {

    @NotNull(message = "Order ID cannot be null")
    private UUID orderId;

    @NotNull(message = "Weight cannot be null")
    private Double weight;
}
