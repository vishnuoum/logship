package com.logship.user.serivce.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ExceptionManager {

    private static final Map<String, UserServiceException> exceptionMap;
    private ExceptionManager(){}

    public static class ERRORCODE {
        private ERRORCODE(){}
        public static final String UNEXPECTED_ERROR = "E1000";
        public static final String VALIDATION_ERROR = "E1001";
        public static final String USER_SAVE_ERROR = "E1002";
        public static final String USER_CREDENTIALS_ERROR = "E1003";
        public static final String USER_NOT_FOUND_ERROR = "E1004";
        public static final String ACCESS_DENIED_ERROR = "E1005";
    }

    public static class ERRORMESSAGE {
        private ERRORMESSAGE(){}
        public static final String UNEXPECTED_ERROR = "An unexpected error occurred";
        public static final String VALIDATION_ERROR = "Request Validation error: [";
        public static final String USER_SAVE_ERROR = "Error while saving user";
        public static final String USER_CREDENTIALS_ERROR = "Wrong User Credentials";
        public static final String USER_NOT_FOUND_ERROR = "User not found";
        public static final String ACCESS_DENIED_ERROR = "Access denied to resource";
    }

    static {
        exceptionMap = new HashMap<>();
        exceptionMap.put(ERRORCODE.UNEXPECTED_ERROR, new UserServiceException(ERRORCODE.UNEXPECTED_ERROR,
                ERRORMESSAGE.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.USER_SAVE_ERROR, new UserServiceException(ERRORCODE.USER_SAVE_ERROR,
                ERRORMESSAGE.USER_SAVE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        exceptionMap.put(ERRORCODE.USER_CREDENTIALS_ERROR, new UserServiceException(ERRORCODE.USER_CREDENTIALS_ERROR,
                ERRORMESSAGE.USER_CREDENTIALS_ERROR, HttpStatus.UNAUTHORIZED));
        exceptionMap.put(ERRORCODE.USER_NOT_FOUND_ERROR, new UserServiceException(ERRORCODE.USER_NOT_FOUND_ERROR,
                ERRORMESSAGE.USER_NOT_FOUND_ERROR, HttpStatus.EXPECTATION_FAILED));
        exceptionMap.put(ERRORCODE.ACCESS_DENIED_ERROR, new UserServiceException(ERRORCODE.ACCESS_DENIED_ERROR,
                ERRORMESSAGE.ACCESS_DENIED_ERROR, HttpStatus.UNAUTHORIZED));
    }

    public static UserServiceException throwException(String errorCode) {
        return exceptionMap.get(errorCode);
    }
}
