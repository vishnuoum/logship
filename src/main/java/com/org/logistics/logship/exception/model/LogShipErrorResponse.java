package com.org.logistics.logship.exception.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogShipErrorResponse {

    private String errorCode;
    private String errorMessage;
}
