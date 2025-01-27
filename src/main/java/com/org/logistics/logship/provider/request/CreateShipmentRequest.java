package com.org.logistics.logship.provider.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShipmentRequest {

    private String handlerId;
    private String toWarehouseId;
}
