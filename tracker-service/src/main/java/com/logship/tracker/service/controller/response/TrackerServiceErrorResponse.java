package com.logship.tracker.service.controller.response;

public class TrackerServiceErrorResponse {

    public final String code;
    public final String message;

    public TrackerServiceErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
