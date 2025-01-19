package com.org.logistics.logship.provider.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderDtoRequest {

    private String orderName;
    private String orderNote;
    private String userId;
    private boolean isFragile;
    private String admissionWarehouseId;
    private String destinationWarehouseId;
}
