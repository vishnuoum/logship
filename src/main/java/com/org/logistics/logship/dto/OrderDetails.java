package com.org.logistics.logship.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
public class OrderDetails {

    private Integer orderId;
    private String orderName;
    private String orderNote;
    private Integer senderId;
    private boolean fragile;
    private Integer admissionWarehouseId;
    private Integer destinationWarehouseId;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
    private String data;
}
