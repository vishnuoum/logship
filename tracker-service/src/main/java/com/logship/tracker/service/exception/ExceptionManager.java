package com.logship.tracker.service.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ExceptionManager {

    private static final Map<String, TrackerServiceException> exceptionMap;
    private ExceptionManager(){}

    public static class ERRORCODE {
        private ERRORCODE(){}
        public static final String UNEXPECTED_ERROR = "E1000";
        public static final String ORDER_NOT_EXISTS_ERROR = "E1001";
        public static final String VALIDATION_ERROR = "E1002";
        public static final String ACCESS_DENIED_ERROR = "E1003";
    }

    public static class ERRORMESSAGE {
        private ERRORMESSAGE(){}
        public static final String UNEXPECTED_ERROR = "An unexpected error occurred";
        public static final String ORDER_NOT_EXISTS_ERROR = "Order status does not exists for given order id";
        public static final String VALIDATION_ERROR = "Request Validation error: [";
        public static final String ACCESS_DENIED_ERROR = "Access denied to resource";
    }

    static {
        exceptionMap = new HashMap<>();
        exceptionMap.put(ERRORCODE.UNEXPECTED_ERROR, new TrackerServiceException(ERRORCODE.UNEXPECTED_ERROR,
                ERRORMESSAGE.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.ORDER_NOT_EXISTS_ERROR, new TrackerServiceException(ERRORCODE.ORDER_NOT_EXISTS_ERROR,
                ERRORMESSAGE.ORDER_NOT_EXISTS_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.ACCESS_DENIED_ERROR, new TrackerServiceException(ERRORCODE.ACCESS_DENIED_ERROR,
                ERRORMESSAGE.ACCESS_DENIED_ERROR, HttpStatus.UNAUTHORIZED));
    }

    public static TrackerServiceException throwException(String errorCode) {
        return exceptionMap.get(errorCode);
    }
}
