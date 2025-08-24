package com.logship.shipment.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class OrderCreatedEventDTO {
    private UUID orderId;
    private Date timestamp;
}
