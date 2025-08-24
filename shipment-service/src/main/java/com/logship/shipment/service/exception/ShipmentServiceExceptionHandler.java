package com.logship.shipment.service.exception;

import com.logship.shipment.service.controller.response.ShipmentServiceErrorResponse;
import com.logship.shipment.service.logging.LogUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ShipmentServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ShipmentServiceErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new ShipmentServiceErrorResponse(ExceptionManager.ERRORCODE.VALIDATION_ERROR,
                ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(", ", ExceptionManager.ERRORMESSAGE.VALIDATION_ERROR,"]"))),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShipmentServiceException.class)
    public ResponseEntity<ShipmentServiceErrorResponse> handleCustomException(ShipmentServiceException ex) {
        return new ResponseEntity<>(new ShipmentServiceErrorResponse(ex.code,ex.message), ex.status);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ShipmentServiceErrorResponse> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        LogUtil.printInfo(ShipmentServiceExceptionHandler.class, "Access denied to resource");
        return handleCustomException(ExceptionManager.throwException(ExceptionManager.ERRORCODE.ACCESS_DENIED_ERROR));
    }

    // Optional: handle all other exceptions without trace
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ShipmentServiceErrorResponse> handleGeneralError(Exception ex) {
        LogUtil.printInfo(ShipmentServiceExceptionHandler.class, ex);
        return handleCustomException(ExceptionManager.throwException(ExceptionManager.ERRORCODE.UNEXPECTED_ERROR));
    }
}
