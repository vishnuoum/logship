package com.org.logistics.logship.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class OrderStatus {

    private Integer statusId;
    private Integer orderId;
    private Integer handlerId;
    private String status;
    private Date createdDate;
}
