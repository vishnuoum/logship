package com.org.logistics.logship.exception;

import org.springframework.http.HttpStatus;

public class ExceptionManager {

    private ExceptionManager(){}

    public static final ExceptionFactory exceptionFactory;

    public static class ErrorCode {

        private ErrorCode() {}

        public static final String DB_READ_ERROR = "DB_READ_ERROR";
        public static final String DB_WRITE_ERROR = "DB_WRITE_ERROR";
    }

    public static class ErrorMessage {

        private ErrorMessage() {}

        public static final String DB_READ_ERROR = "Error while reading data from DB";
        public static final String DB_WRITE_ERROR = "Error while writing data to DB";
    }

    static {
        exceptionFactory = new ExceptionFactory();
        exceptionFactory.add(ErrorCode.DB_READ_ERROR, ErrorMessage.DB_READ_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionFactory.add(ErrorCode.DB_WRITE_ERROR, ErrorMessage.DB_WRITE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
