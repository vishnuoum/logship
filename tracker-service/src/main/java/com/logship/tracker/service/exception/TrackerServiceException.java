package com.logship.tracker.service.exception;

import org.springframework.http.HttpStatus;

public class TrackerServiceException extends RuntimeException {

    public final HttpStatus status;
    public final String code;
    public final String message;
    public TrackerServiceException(String code, String message, HttpStatus status) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
