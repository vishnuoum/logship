package com.logship.tracker.service.dto;

import com.logship.tracker.service.entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class OrderUpdateEventDTO {
    private UUID orderId;
    private Date timestamp;
    private Status status;
    private Long warehouseId;
}
