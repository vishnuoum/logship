package com.logship.user.serivce.controller.response;

public class UserServiceErrorResponse {
    public final String code;
    public final String message;
    public UserServiceErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
