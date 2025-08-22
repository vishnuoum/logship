package com.logship.warehouse.service.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddToInventoryRequest {

    @NotNull(message = "Order Id cannot be null")
    private UUID orderId;

    @NotNull(message = "Warehouse Id cannot be null")
    private Long warehouseId;
}
