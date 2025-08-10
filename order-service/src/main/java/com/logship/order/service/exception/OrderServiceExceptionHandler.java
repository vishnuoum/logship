package com.logship.order.service.exception;

import com.logship.order.service.controller.response.OrderServiceErrorResponse;
import com.logship.order.service.logging.LogUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class OrderServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<OrderServiceErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new OrderServiceErrorResponse(ExceptionManager.ERRORCODE.VALIDATION_ERROR,
                ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(", ", ExceptionManager.ERRORMESSAGE.VALIDATION_ERROR,"]"))),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderServiceException.class)
    public ResponseEntity<OrderServiceErrorResponse> handleCustomException(OrderServiceException ex) {
        return new ResponseEntity<>(new OrderServiceErrorResponse(ex.code,ex.message), ex.status);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<OrderServiceErrorResponse> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        LogUtil.printInfo(OrderServiceExceptionHandler.class, "Access denied to resource");
        return handleCustomException(ExceptionManager.throwException(ExceptionManager.ERRORCODE.ACCESS_DENIED_ERROR));
    }

    // Optional: handle all other exceptions without trace
    @ExceptionHandler(Exception.class)
    public ResponseEntity<OrderServiceErrorResponse> handleGeneralError(Exception ex) {
        LogUtil.printInfo(OrderServiceExceptionHandler.class, ex);
        return handleCustomException(ExceptionManager.throwException(ExceptionManager.ERRORCODE.UNEXPECTED_ERROR));
    }
}
