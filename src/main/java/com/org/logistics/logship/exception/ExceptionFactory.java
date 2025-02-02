package com.org.logistics.logship.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ExceptionFactory {

    private static final Map<String, LogShipError> errorMap = new HashMap<>();

    public void add(String errorCode, String errorMessage, HttpStatus httpStatus) {
        errorMap.put(errorCode, new LogShipError(errorCode, errorMessage, httpStatus));
    }

    public LogShipError generateException(String errorCode) {
        return errorMap.get(errorCode);
    }
}
