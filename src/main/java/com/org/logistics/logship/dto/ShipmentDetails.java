package com.org.logistics.logship.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class ShipmentDetails {
    private Integer shipmentId;
    private Integer orderId;
    private LocalDateTime addedDate;
    private LocalDateTime endDate;
}
