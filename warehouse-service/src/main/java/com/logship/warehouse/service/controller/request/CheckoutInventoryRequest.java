package com.logship.warehouse.service.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CheckoutInventoryRequest {

    @NotEmpty(message = "Order ID cannot be null")
    private UUID orderId;
    @NotEmpty(message = "Warehouse ID cannot be null")
    private Long warehouseId;
}
