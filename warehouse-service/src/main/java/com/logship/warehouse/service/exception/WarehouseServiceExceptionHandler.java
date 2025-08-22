package com.logship.warehouse.service.exception;

import com.logship.warehouse.service.controller.response.WarehouseServiceErrorResponse;
import com.logship.warehouse.service.logging.LogUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class WarehouseServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WarehouseServiceErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new WarehouseServiceErrorResponse(ExceptionManager.ERRORCODE.VALIDATION_ERROR,
                ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(", ", ExceptionManager.ERRORMESSAGE.VALIDATION_ERROR,"]"))),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WarehouseServiceException.class)
    public ResponseEntity<WarehouseServiceErrorResponse> handleCustomException(WarehouseServiceException ex) {
        return new ResponseEntity<>(new WarehouseServiceErrorResponse(ex.code,ex.message), ex.status);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<WarehouseServiceErrorResponse> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        LogUtil.printInfo(WarehouseServiceExceptionHandler.class, "Access denied to resource");
        return handleCustomException(ExceptionManager.throwException(ExceptionManager.ERRORCODE.ACCESS_DENIED_ERROR));
    }

    // Optional: handle all other exceptions without trace
    @ExceptionHandler(Exception.class)
    public ResponseEntity<WarehouseServiceErrorResponse> handleGeneralError(Exception ex) {
        LogUtil.printInfo(WarehouseServiceExceptionHandler.class, ex);
        return handleCustomException(ExceptionManager.throwException(ExceptionManager.ERRORCODE.UNEXPECTED_ERROR));
    }
}
