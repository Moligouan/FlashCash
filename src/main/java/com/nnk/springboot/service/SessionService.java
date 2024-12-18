package com.nnk.springboot.service;

import com.nnk.springboot.model.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final UserRepository userRepository;

    public SessionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User sessionUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findUserByMail(email)
                .orElseThrow(() -> new IllegalStateException("User not found with email: " + email));
    }
}
