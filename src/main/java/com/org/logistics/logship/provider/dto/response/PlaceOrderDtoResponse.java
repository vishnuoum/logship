package com.org.logistics.logship.provider.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PlaceOrderDtoResponse implements Serializable {

    private String orderInfo;
}
