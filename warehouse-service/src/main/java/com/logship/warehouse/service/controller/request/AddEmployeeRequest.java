package com.logship.warehouse.service.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddEmployeeRequest {

    @NotNull(message = "User ID cannot be null")
    private UUID userId;
    @NotNull(message = "Warehouse ID cannot be null")
    private Long warehouseId;
}
