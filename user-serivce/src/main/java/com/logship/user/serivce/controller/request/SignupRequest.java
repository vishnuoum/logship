package com.logship.user.serivce.controller.request;

import com.logship.user.serivce.entity.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Employee name cannot be empty")
    private String employeeName;

    @Size(min = 8, max = 20, message = "Password length does not match requirement (Min: 8, Max: 20)")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "Role should be selected")
    private Role role;
}
