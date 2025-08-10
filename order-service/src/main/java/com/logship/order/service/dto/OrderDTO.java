package com.logship.order.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderDTO {

    private UUID id;
    private String pickupAddress;
    private String dropAddress;
    private boolean isFragile;
    private String remarks;
}
