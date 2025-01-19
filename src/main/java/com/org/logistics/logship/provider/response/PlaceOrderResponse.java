package com.org.logistics.logship.provider.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PlaceOrderResponse implements Serializable {

    private String orderInfo;
}
