package com.logship.tracker.service.exception;

import com.logship.tracker.service.controller.response.TrackerServiceErrorResponse;
import com.logship.tracker.service.logging.LogUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class TrackerServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<TrackerServiceErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new TrackerServiceErrorResponse(ExceptionManager.ERRORCODE.VALIDATION_ERROR,
                ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(", ", ExceptionManager.ERRORMESSAGE.VALIDATION_ERROR,"]"))),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TrackerServiceException.class)
    public ResponseEntity<TrackerServiceErrorResponse> handleCustomException(TrackerServiceException ex) {
        return new ResponseEntity<>(new TrackerServiceErrorResponse(ex.code,ex.message), ex.status);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<TrackerServiceErrorResponse> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        LogUtil.printInfo(this.getClass(), "Access denied to resource");
        return handleCustomException(ExceptionManager.throwException(ExceptionManager.ERRORCODE.ACCESS_DENIED_ERROR));
    }

    // Optional: handle all other exceptions without trace
    @ExceptionHandler(Exception.class)
    public ResponseEntity<TrackerServiceErrorResponse> handleGeneralError(Exception ex) {
        LogUtil.printInfo(this.getClass(), ex);
        return handleCustomException(ExceptionManager.throwException(ExceptionManager.ERRORCODE.UNEXPECTED_ERROR));
    }
}
