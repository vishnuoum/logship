package com.logship.shipment.service.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartShipmentRequest {
    @NotNull(message = "Shipment ID cannot be null")
    private Long shipmentId;
}
