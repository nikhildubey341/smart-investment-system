package com.smartinvest.auth.service;

import com.smartinvest.auth.entity.User;
import com.smartinvest.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user){

        userRepository.findByEmail(user.getEmail())
                .ifPresent(u ->{
                    throw new RuntimeException("Email Already Exists");
                });

        return userRepository.save(user);
    }

}
