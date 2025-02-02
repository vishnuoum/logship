package com.org.logistics.logship.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ShipmentMaster {

    private Integer shipmentId;
    private Integer fromWarehouseId;
    private Integer toWarehouseId;
    private Integer shipmentHandlerId;
    private LocalDateTime createdDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
