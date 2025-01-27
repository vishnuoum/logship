package com.org.logistics.logship.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class HandlerDetails {

    private Integer handlerId;
    private String handlerName;
    private Integer warehouseId;
    private Date datetime;
}
