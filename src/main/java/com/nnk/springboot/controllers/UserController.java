package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Transfer;
import com.nnk.springboot.model.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.LinkService;
import com.nnk.springboot.service.SessionService;
import com.nnk.springboot.service.TransferService;
import com.nnk.springboot.service.UserService;
import com.nnk.springboot.service.form.SignInForm;
import com.nnk.springboot.service.form.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    private final LinkService linkService;
    private final UserService userService;
    private final TransferService transferService;
    private final SessionService sessionService;

    public UserController(LinkService linkService, UserService userService, TransferService transferService, SessionService sessionService) {
        this.linkService = linkService;
        this.userService = userService;
        this.transferService = transferService;
        this.sessionService = sessionService;
    }

    @GetMapping("/")
    public ModelAndView home(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
//        List<Transfer> transactions = transferService.findTransaction();
//        model.addAttribute("transfers", transactions);
        return new ModelAndView("index");
    }

    @PostMapping("/signup")
    public ModelAndView processRequest(@ModelAttribute("signUpForm") SignUpForm form)
    {
        userService.registration(form);
        return new ModelAndView("signin", "signInForm", new SignInForm());
    }

    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signUpForm", new SignUpForm());
    }

    @GetMapping("/home")
    public String logOff(Model model) {
        return "home";
    }

    @GetMapping("profile")
    public ModelAndView profile(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("profile");
    }

//    @GetMapping("add-iban")
//    public ModelAndView getAddConnectionForm(Model model) {
//        return new ModelAndView("add-iban", "addIbanForm", new AddIbanForm());
//    }

//    @PostMapping("add-iban")
//    public ModelAndView addIban(Model model, @ModelAttribute("addIbanForm") AddIbanForm form) {
//        userService.addIban(form);
//        User user = sessionService.sessionUser();
//        model.addAttribute("user", user);
//        return new ModelAndView("profile");
//    }

//    @GetMapping("email")
//    public ModelAndView getEmailForm(Model model) {
//        return new ModelAndView("email", "emailForm", new EmailForm());
//    }
//
//    @PostMapping("email")
//    public ModelAndView sendEmail(Model model, @ModelAttribute("EmailForm") EmailForm form) {
//        userService.addEmail(form);
//        User user = sessionService.sessionUser();
//        model.addAttribute("user", user);
//        return new ModelAndView("profile");
//    }
}
