package com.sivateja.springbootjpademo.controller;

import com.sivateja.springbootjpademo.dto.LoginRequest;
import com.sivateja.springbootjpademo.dto.LoginResponse;
import com.sivateja.springbootjpademo.entity.AppUser;
import com.sivateja.springbootjpademo.service.AuthService;
import com.sivateja.springbootjpademo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.authenticate(loginRequest);
    }

    @PostMapping("/logout")
    public Boolean logout() {
       return false;
    }

    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser user) {
        return userService.save(user);
    }
}
