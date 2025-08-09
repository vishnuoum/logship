package com.logship.user.serivce.controller;

import com.logship.user.serivce.controller.request.LoginRequest;
import com.logship.user.serivce.controller.request.SignupRequest;
import com.logship.user.serivce.controller.response.LoginResponse;
import com.logship.user.serivce.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpServletRequest servletRequest) {
        return ResponseEntity.ok(authService.validateUser(request, servletRequest.getRemoteAddr(), servletRequest.getHeader("User-Agent")));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest request) {
        authService.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
