package com.org.logistics.logship.provider.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddShipmentOrdersRequest {

    @NotEmpty(message = "Shipment Id shall not be null or empty")
    @Pattern(regexp = "^S\\d+$", message = "Shipment Id pattern not matched")
    private String shipmentId;

    @NotEmpty(message = "Shipment Orders must not be null or empty")
    @Pattern(regexp = "^O\\d+$", message = "Order Id pattern not matched")
    private List<String> shipmentOrders;
}
