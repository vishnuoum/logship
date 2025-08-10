package com.logship.order.service.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ExceptionManager {

    private static final Map<String, OrderServiceException> exceptionMap;
    private ExceptionManager(){}

    public static class ERRORCODE {
        private ERRORCODE(){}
        public static final String UNEXPECTED_ERROR = "E1000";
        public static final String VALIDATION_ERROR = "E1001";
        public static final String ACCESS_DENIED_ERROR = "E1003";
        public static final String ORDER_SAVE_ERROR = "E1004";
        public static final String ORDER_FETCH_ERROR = "E1005";
        public static final String USER_NOT_FOUND_ERROR = "E1006";
        public static final String ORDER_NOT_EXISTS_ERROR = "E1007";
    }

    public static class ERRORMESSAGE {
        private ERRORMESSAGE(){}
        public static final String UNEXPECTED_ERROR = "An unexpected error occurred";
        public static final String VALIDATION_ERROR = "Request Validation error: [";
        public static final String ACCESS_DENIED_ERROR = "Access denied to resource";
        public static final String ORDER_SAVE_ERROR = "Error while creating the order";
        public static final String ORDER_FETCH_ERROR = "Error while fetching order";
        public static final String USER_NOT_FOUND_ERROR = "User not found";
        public static final String ORDER_NOT_EXISTS_ERROR = "Order does not exists";
    }

    static {
        exceptionMap = new HashMap<>();
        exceptionMap.put(ERRORCODE.UNEXPECTED_ERROR, new OrderServiceException(ERRORCODE.UNEXPECTED_ERROR,
                ERRORMESSAGE.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.ACCESS_DENIED_ERROR, new OrderServiceException(ERRORCODE.ACCESS_DENIED_ERROR,
                ERRORMESSAGE.ACCESS_DENIED_ERROR, HttpStatus.UNAUTHORIZED));
        exceptionMap.put(ERRORCODE.ORDER_SAVE_ERROR, new OrderServiceException(ERRORCODE.ORDER_SAVE_ERROR,
                ERRORMESSAGE.ORDER_SAVE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.ORDER_FETCH_ERROR, new OrderServiceException(ERRORCODE.ORDER_FETCH_ERROR,
                ERRORMESSAGE.ORDER_FETCH_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.USER_NOT_FOUND_ERROR, new OrderServiceException(ERRORCODE.USER_NOT_FOUND_ERROR,
                ERRORMESSAGE.USER_NOT_FOUND_ERROR, HttpStatus.UNAUTHORIZED));
        exceptionMap.put(ERRORCODE.ORDER_NOT_EXISTS_ERROR, new OrderServiceException(ERRORCODE.ORDER_NOT_EXISTS_ERROR,
                ERRORMESSAGE.ORDER_NOT_EXISTS_ERROR, HttpStatus.UNAUTHORIZED));
    }

    public static OrderServiceException throwException(String errorCode) {
        return exceptionMap.get(errorCode);
    }
}
