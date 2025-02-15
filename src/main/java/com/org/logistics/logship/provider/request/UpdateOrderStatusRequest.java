package com.org.logistics.logship.provider.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateOrderStatusRequest {

    @NotEmpty(message = "Handler id should not be null or empty")
    @Pattern(regexp = "^H\\d+$", message = "Handler Id pattern not matched")
    private String handlerId;

    @NotEmpty(message = "Order id should not be null or empty")
    @Pattern(regexp = "^O\\d+$", message = "Order Id pattern not matched")
    private String orderId;

    @NotEmpty(message = "Shipment id should not be null or empty")
    @Pattern(regexp = "^S\\d+$", message = "Shipment Id pattern not matched")
    private String shipmentId;
}
