package com.sivateja.springbootjpademo.service;

import com.sivateja.springbootjpademo.dto.LoginRequest;
import com.sivateja.springbootjpademo.dto.LoginResponse;
import com.sivateja.springbootjpademo.utils.JWTUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private AuthenticationManager authenticationManager;

    public LoginResponse authenticate(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        LoginResponse response = JWTUtils.crateJWTToken(authentication);
        return response;
    }
}
