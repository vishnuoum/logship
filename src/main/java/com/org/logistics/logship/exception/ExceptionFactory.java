package com.org.logistics.logship.exception;

import com.org.logistics.logship.exception.model.LogShipException;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ExceptionFactory {

    private static final Map<String, LogShipException> errorMap = new HashMap<>();

    public void add(String errorCode, String errorMessage, HttpStatus httpStatus) {
        errorMap.put(errorCode, new LogShipException(errorCode, errorMessage, httpStatus));
    }

    public LogShipException generateException(String errorCode) {
        return errorMap.get(errorCode);
    }
}
