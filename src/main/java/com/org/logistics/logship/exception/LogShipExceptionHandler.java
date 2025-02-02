package com.org.logistics.logship.exception;

import com.org.logistics.logship.exception.model.LogShipException;
import com.org.logistics.logship.exception.model.LogShipErrorResponse;
import com.org.logistics.logship.logging.LoggerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class LogShipExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = LogShipException.class)
    public ResponseEntity<LogShipErrorResponse> handleConflict(LogShipException ex) {
        LogShipErrorResponse errorResponse = new LogShipErrorResponse();
        errorResponse.setErrorCode(ex.getErrorCode());
        errorResponse.setErrorMessage(ex.getErrorMessage());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatusCode());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<LogShipErrorResponse> handlerGenericConflict(Exception ex) {
        LoggerUtil.printError(ex.getMessage());
        LogShipErrorResponse errorResponse = new LogShipErrorResponse();
        errorResponse.setErrorCode("GENERIC_ERROR");
        errorResponse.setErrorMessage("Please contact service provider");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
