package com.org.logistics.logship.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ShipmentMaster {

    private Integer shipmentId;
    private Integer fromWarehouseId;
    private Integer toWarehouseId;
    private Integer shipmentHandlerId;
    private Date createdDate;
    private Date startDate;
    private Date endDate;
}
