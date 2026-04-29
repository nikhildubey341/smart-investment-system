package com.smartinvest.auth.service;

import com.smartinvest.auth.dto.login.LoginRequest;
import com.smartinvest.auth.dto.login.LoginResponse;
import com.smartinvest.auth.dto.register.RegisterRequest;
import com.smartinvest.auth.dto.register.RegisterResponse;
import com.smartinvest.auth.entity.User;
import com.smartinvest.auth.repository.UserRepository;
import com.smartinvest.auth.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    public RegisterResponse register(RegisterRequest request){

        userRepository.findByEmail(request.getEmail())
                .ifPresent((u->{
                    throw new RuntimeException("Email Already Exists");
                }));

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        User saved = userRepository.save(user);

        return new RegisterResponse(
                saved.getId(),
                saved.getName(),
                saved.getEmail()
        );
    }

    public LoginResponse login(LoginRequest request){

        User user  = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }

}
