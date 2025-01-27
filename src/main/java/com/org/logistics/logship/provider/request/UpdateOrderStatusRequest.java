package com.org.logistics.logship.provider.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusRequest {

    private String handlerId;
    private String orderId;
}
