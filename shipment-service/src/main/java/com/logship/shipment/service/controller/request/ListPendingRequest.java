package com.logship.shipment.service.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListPendingRequest {

    private List<@NotEmpty(message = "PinCode cannot be empty") @Size(max = 6, min = 6, message = "PinCode must be length of 6") String> pinCodes;
}
