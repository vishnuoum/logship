package com.org.logistics.logship.exception.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class LogShipException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatusCode httpStatusCode;

    public LogShipException(String errorCode, String errorMessage, HttpStatusCode httpStatusCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
    }

    public LogShipException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
