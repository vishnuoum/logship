package com.org.logistics.logship.provider.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateShipmentRequest {

    @NotEmpty(message = "Handler ID should not be null or empty")
    @Pattern(regexp = "^H\\d+$", message = "Handler Id pattern not matched")
    private String handlerId;

    @NotEmpty(message = "Warehouse id should not be null or empty")
    @Pattern(regexp = "^W\\d+$", message = "Warehouse Id pattern not matched")
    private String toWarehouseId;
}
