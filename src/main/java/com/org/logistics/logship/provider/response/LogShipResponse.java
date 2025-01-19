package com.org.logistics.logship.provider.response;

import com.org.logistics.logship.exception.LogShipErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class LogShipResponse {

    private LogShipResponse() {}


    public static <T> ResponseEntity<T> ok(T data) {
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    public static <T extends LogShipErrorResponse> ResponseEntity<LogShipErrorResponse> error(T data) {
        return new ResponseEntity<>((LogShipErrorResponse) data, ((LogShipErrorResponse) data).getHttpStatusCode());
    }
}
