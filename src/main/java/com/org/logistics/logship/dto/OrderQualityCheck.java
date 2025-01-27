package com.org.logistics.logship.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderQualityCheck {

    private Integer orderCheckId;
    private Integer orderId;
    private Integer qualityCheckId;
}
