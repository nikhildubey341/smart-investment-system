package com.smartinvest.auth.controller;

import com.smartinvest.auth.dto.login.LoginRequest;
import com.smartinvest.auth.dto.login.LoginResponse;
import com.smartinvest.auth.dto.register.RegisterRequest;
import com.smartinvest.auth.dto.register.RegisterResponse;
import com.smartinvest.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
   public RegisterResponse register(@Valid @RequestBody RegisterRequest request){
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request){
        return userService.login(request);
    }
}
