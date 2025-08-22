package com.logship.warehouse.service.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateWarehouseRequest {

    @NotEmpty(message = "Warehouse name cannot be null")
    private String name;
    @NotEmpty(message = "Warehouse address cannot be null")
    private String address;

    @NotNull(message = "Pin Codes cannot be null")
    private List<@NotNull @Pattern(regexp = "\\d{6}", message = "Pincode must be exactly 6 digits") String> pinCodes;
}
