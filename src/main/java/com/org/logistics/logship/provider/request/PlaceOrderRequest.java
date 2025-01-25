package com.org.logistics.logship.provider.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaceOrderRequest {
    private String orderName;
    private String orderNote;
    private String handlerId;
    private String userId;
    private boolean fragile;
    private String admissionWarehouseId;
    private String destinationWarehouseId;
    private List<String> qualityCheckIds;
}
