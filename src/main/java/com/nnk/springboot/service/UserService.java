package com.nnk.springboot.service;

import com.nnk.springboot.model.User;
import com.nnk.springboot.model.UserAccount;
import com.nnk.springboot.repositories.UserAccountRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.form.SignUpForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final SessionService sessionService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;

    public UserService(SessionService sessionService, PasswordEncoder passwordEncoder, UserRepository userRepository, UserAccountRepository userAccountRepository) {
        this.sessionService = sessionService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
    }

    public User registration(SignUpForm form) {
        User user = new User();
        UserAccount account = new UserAccount();
        account.setAmount(0.0);
        user.setAccount(account);
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        return userRepository.save(user);
    }
}
