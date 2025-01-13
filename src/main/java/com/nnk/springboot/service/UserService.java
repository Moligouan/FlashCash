package com.nnk.springboot.service;

import com.nnk.springboot.model.User;
import com.nnk.springboot.model.UserAccount;
import com.nnk.springboot.repositories.UserAccountRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.form.AddIbanForm;
import com.nnk.springboot.service.form.DepotForm;
import com.nnk.springboot.service.form.SignUpForm;
import com.nnk.springboot.utils.IBANFormatter;
import com.nnk.springboot.utils.IBANValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final SessionService sessionService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(SessionService sessionService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.sessionService = sessionService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            return false; // Moins de 8 caractères
        }
        if (!password.matches(".*[A-Z].*")) {
            return false; // Pas de majuscule
        }
        if (!password.matches(".*[a-z].*")) {
            return false; // Pas de minuscule
        }
        if (!password.matches(".*\\d.*")) {
            return false; // Pas de chiffre
        }
        if (!password.matches(".*[@#$%^&+=!_?<>*(){}\\[\\]\\\\|~`\"';:.,].*")) {
            return false; // Pas de caractère spécial
        }
        return true; // Tous les critères sont remplis
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

    public void AddMoney(DepotForm form, User user) {
        UserAccount account = user.getAccount();
        Double montant = account.getAmount() + form.getAmount();
        account.setAmount(montant);
        user.setAccount(account);
        userRepository.save(user);
    }

    public void PickMoney(DepotForm form, User user) {
        UserAccount account = user.getAccount();
        Double montant = account.getAmount() - form.getAmount();
        account.setAmount(montant);
        user.setAccount(account);
        userRepository.save(user);
    }

    public void AddIban(AddIbanForm form, User user) {
        UserAccount account = user.getAccount();
        String iban = IBANFormatter.cleanIBAN(form.getIban());
        if (!IBANValidator.isValidIBAN(iban)) {
            throw new IllegalArgumentException("Invalid IBAN format.");
        }
        account.setIban(iban);
        user.setAccount(account);
        userRepository.save(user);
    }

    public boolean checkUser(String email) {
        return userRepository.findUserByMail(email).isPresent();
    }

    public boolean checkRetrait(DepotForm form, User user) {
        UserAccount userAccount = user.getAccount();
        return form.getAmount() <= userAccount.getAmount();
    }

    public void transfer(DepotForm form, User user, Integer targetId) {
        UserAccount account = user.getAccount();
        Double montant = account.getAmount() - form.getAmount();
        account.setAmount(montant);
        user.setAccount(account);
        userRepository.save(user);

        User target = userRepository.findUserById(targetId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + targetId));
        UserAccount targetAccount = target.getAccount();
        Double transfer = targetAccount.getAmount() + (form.getAmount() * 995/1000);
        targetAccount.setAmount(transfer);
        target.setAccount(targetAccount);
        userRepository.save(target);
    }

    public User targeting(Integer targetId){
        return userRepository.findUserById(targetId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + targetId));
    }
}
