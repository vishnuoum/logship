package com.logship.shipment.service.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateShipmentRequest {

    @NotNull(message = "From Warehouse ID cannot be null")
    private Long fromWarehouseId;
    @NotEmpty(message = "To Warehouse ID cannot be null")
    private String toWarehouseId;
    @NotNull(message = "Driver ID cannot be null")
    private UUID driverId;
    @NotNull(message = "Orders cannot be null")
    private List<@NotNull(message = "Order ID cannot be null") UUID> orders;
}
