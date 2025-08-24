package com.logship.shipment.service.controller.request;

import com.logship.shipment.service.controller.request.model.OrderDetails;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ScheduleDeliveryRequest {

    @NotNull(message = "Driver ID cannot be null")
    private UUID driverId;
    @NotNull(message = "Order Details cannot be null")
    private List<OrderDetails> orderDetails;
}
