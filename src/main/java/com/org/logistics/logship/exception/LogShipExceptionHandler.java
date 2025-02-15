package com.org.logistics.logship.exception;

import com.org.logistics.logship.exception.model.LogShipException;
import com.org.logistics.logship.exception.model.LogShipErrorResponse;
import com.org.logistics.logship.logging.LoggerUtil;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Hidden
@ControllerAdvice
public class LogShipExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        LoggerUtil.printError(ex.getMessage());
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        LogShipErrorResponse errorResponse = new LogShipErrorResponse();
        errorResponse.setErrorCode("VALIDATION_ERROR");
        errorResponse.setErrorMessage(errors.toString());
        return handleExceptionInternal(
                ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = LogShipException.class)
    public ResponseEntity<LogShipErrorResponse> handleConflict(LogShipException ex) {
        LoggerUtil.printError(ex.getMessage());
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
