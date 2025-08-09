package com.logship.user.serivce.exception;

import com.logship.user.serivce.controller.response.UserServiceErrorResponse;
import com.logship.user.serivce.logging.LogUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class UserServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserServiceErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new UserServiceErrorResponse(ExceptionManager.ERRORCODE.VALIDATION_ERROR,
                ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(", ", ExceptionManager.ERRORMESSAGE.VALIDATION_ERROR,"]"))),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<UserServiceErrorResponse> handleCustomException(UserServiceException ex) {
        return new ResponseEntity<>(new UserServiceErrorResponse(ex.code,ex.message), ex.status);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<UserServiceErrorResponse> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        LogUtil.printInfo(UserServiceExceptionHandler.class, "Access denied to resource");
        return handleCustomException(ExceptionManager.throwException(ExceptionManager.ERRORCODE.ACCESS_DENIED_ERROR));
    }

    // Optional: handle all other exceptions without trace
    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserServiceErrorResponse> handleGeneralError(Exception ex) {
        LogUtil.printInfo(UserServiceExceptionHandler.class, ex);
        return handleCustomException(ExceptionManager.throwException(ExceptionManager.ERRORCODE.UNEXPECTED_ERROR));
    }
}
