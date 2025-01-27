package com.org.logistics.logship.provider.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddShipmentOrdersRequest {

    private String shipmentId;
    private List<String> shipmentOrders;
}
