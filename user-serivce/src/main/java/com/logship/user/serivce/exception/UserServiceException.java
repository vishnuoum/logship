package com.logship.user.serivce.exception;

import org.springframework.http.HttpStatus;

public class UserServiceException extends RuntimeException {
    public final HttpStatus status;
    public final String code;
    public final String message;
    public UserServiceException(String code, String message, HttpStatus status) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
