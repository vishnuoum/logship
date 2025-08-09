package com.logship.user.serivce.controller;

import com.logship.user.serivce.dto.UserDTO;
import com.logship.user.serivce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Hi");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> me(Authentication authentication) {
        return ResponseEntity.ok(userService.getUser(authentication.getName()));
    }
}
