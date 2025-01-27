package com.org.logistics.logship.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HandlerDetails {

    private Integer handlerId;
    private String handlerName;
    private Integer warehouseId;
    private LocalDateTime joinedDate;
}
