package com.logship.user.serivce.service;

import com.logship.user.serivce.controller.request.LoginRequest;
import com.logship.user.serivce.controller.request.SignupRequest;
import com.logship.user.serivce.controller.response.LoginResponse;
import com.logship.user.serivce.exception.ExceptionManager;
import com.logship.user.serivce.logging.LogUtil;
import com.logship.user.serivce.mapper.UserMapper;
import com.logship.user.serivce.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public void saveUser(SignupRequest request) {
        try {
            userRepository.save(userMapper.mapSignUpToUser(request, passwordEncoder));
        } catch (Exception e) {
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.USER_SAVE_ERROR);
        }
    }

    public LoginResponse validateUser(LoginRequest request, HttpServletRequest httpServletRequest) {
        String userAgent = Optional.ofNullable(httpServletRequest.getHeader("X-User-Agent")).orElseThrow(() -> ExceptionManager.throwException(ExceptionManager.ERRORCODE.USER_AGENT_NOT_FOUNT_ERROR));
        String ip = Optional.ofNullable(httpServletRequest.getHeader("X-Client-IP")).orElseThrow(() -> ExceptionManager.throwException(ExceptionManager.ERRORCODE.IP_NOT_FOUNT_ERROR));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            String token = jwtService.generateToken(userDetailsService.loadUserByUsername(request.getUsername()), ip, userAgent);
            LoginResponse response = new LoginResponse();
            response.setToken(token);
            return response;
        } catch (BadCredentialsException bce) {
            LogUtil.printInfo(AuthService.class, "Wrong user credentials");
            throw ExceptionManager.throwException(ExceptionManager.ERRORCODE.USER_CREDENTIALS_ERROR);
        }
    }
}
