package com.logship.user.serivce.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotEmpty(message = "Username must not be empty")
    private String username;
    @NotEmpty(message = "Password must not be empty")
    private String password;
}
