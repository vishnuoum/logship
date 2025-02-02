package com.org.logistics.logship.provider.response;

import com.org.logistics.logship.exception.LogShipError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class LogShipResponse {

    private LogShipResponse() {}


    public static <T> ResponseEntity<T> ok(T data) {
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    public static <T extends LogShipError> ResponseEntity<LogShipError> error(T data) {
        return new ResponseEntity<>(data,  data.getHttpStatusCode());
    }
}
