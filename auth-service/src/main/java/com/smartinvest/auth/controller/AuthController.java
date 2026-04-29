package com.smartinvest.auth.controller;

import com.smartinvest.auth.dto.RegisterRequest;
import com.smartinvest.auth.dto.RegisterResponse;
import com.smartinvest.auth.entity.User;
import com.smartinvest.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
   public RegisterResponse response(@Valid @RequestBody RegisterRequest request){
        return userService.register(request);
    }
}
