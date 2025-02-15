package com.org.logistics.logship.provider.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PlaceOrderRequest {

    @NotEmpty(message = "Order name should not be null or empty")
    private String orderName;
    private String orderNote;

    @NotEmpty(message = "Handler id should not be null or empty")
    @Pattern(regexp = "^H\\d+$", message = "Handler Id pattern not matched")
    private String handlerId;

    @NotEmpty(message = "User id should not be null or empty")
    @Pattern(regexp = "^U\\d+$", message = "User Id pattern not matched")
    private String userId;

    @NotNull(message = "Fragile flag shall not be null")
    private boolean fragile;

    @NotEmpty(message = "Admission warehouse id should not be null or empty")
    @Pattern(regexp = "^W\\d+$", message = "Admission warehouse Id pattern not matched")
    private String admissionWarehouseId;

    @NotEmpty(message = "Destination warehouse id should not be null or empty")
    @Pattern(regexp = "^W\\d+$", message = "Destination warehouse Id pattern not matched")
    private String destinationWarehouseId;

    @NotNull(message = "List of quality checks should not be null")
    @Pattern(regexp = "^QC\\d+$", message = "Quality check Id pattern not matched")
    private List<String> qualityCheckIds;
}
