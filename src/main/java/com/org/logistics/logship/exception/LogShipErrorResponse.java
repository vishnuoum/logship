package com.org.logistics.logship.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class LogShipErrorResponse extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatusCode httpStatusCode;

    public LogShipErrorResponse(String errorCode, String errorMessage, HttpStatusCode httpStatusCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
    }
}
