package com.logship.order.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class OrderCreatedEventDTO {
    private UUID orderId;
    private UUID customerId;
    private Date timestamp;

    public OrderCreatedEventDTO() {
        timestamp = new Date();
    }
}
