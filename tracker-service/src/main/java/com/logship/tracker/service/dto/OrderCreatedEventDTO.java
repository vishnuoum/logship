package com.logship.tracker.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class OrderCreatedEventDTO {
    private UUID orderId;
    private Date timestamp;
    private String pickupPinCode;
}
