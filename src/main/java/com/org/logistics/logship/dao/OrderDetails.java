package com.org.logistics.logship.dao;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


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
    private Date createdDate;
    private Date lastUpdatedDate;
    private String data;
}
