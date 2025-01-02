package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Link;
import com.nnk.springboot.model.Transfer;
import com.nnk.springboot.model.User;
import com.nnk.springboot.model.UserAccount;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.LinkService;
import com.nnk.springboot.service.SessionService;
import com.nnk.springboot.service.TransferService;
import com.nnk.springboot.service.UserService;
import com.nnk.springboot.service.form.*;
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
    private final SessionService sessionService;

    public UserController(LinkService linkService, UserService userService, SessionService sessionService) {
        this.linkService = linkService;
        this.userService = userService;
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
    public String processRequest(@ModelAttribute("signUpForm") SignUpForm form)
    {
        userService.registration(form);
        return "redirect:/signin";
    }

    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signUpForm", new SignUpForm());
    }

//    @GetMapping("/home")
//    public String logOff(Model model) {
//        return "home";
//    }

//    @GetMapping("profile")
//    public ModelAndView profile(Model model) {
//        User user = sessionService.sessionUser();
//        model.addAttribute("user", user);
//        return new ModelAndView("profile");
//    }

    @GetMapping("/add-iban")
    public ModelAndView getAddConnectionForm(Model model) {
        return new ModelAndView("account/add-iban", "addIbanForm", new AddIbanForm());
    }

    @PostMapping("add-iban")
    public String addIban(Model model, @ModelAttribute("addIbanForm") AddIbanForm form) {
        User user = sessionService.sessionUser();
        userService.AddIban(form, user);
        return "redirect:/";
    }

    @GetMapping("/depot")
    public ModelAndView getDepotForm(Model model) {
        return new ModelAndView("account/depot", "depotForm", new DepotForm());
    }

    @PostMapping("depot")
    public String updateDepot(Model model, @ModelAttribute("depotForm") DepotForm form) {
        User user = sessionService.sessionUser();
        userService.AddMoney(form, user);
        return "redirect:/";
    }

    @GetMapping("/retrait")
    public ModelAndView getRetraitForm(Model model) {
        return new ModelAndView("account/retrait", "depotForm", new DepotForm());
    }

    @PostMapping("retrait")
    public String updateRetrait(Model model, @ModelAttribute("depotForm") DepotForm form) {
        User user = sessionService.sessionUser();
        userService.PickMoney(form, user);
        return "redirect:/";
    }

    @GetMapping("/friends")
    public ModelAndView friendsList(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("account/friends");
    }

    @GetMapping("/add-friend")
    public ModelAndView getFriendForm(Model model) {
        return new ModelAndView("link/add-friend", "friendForm", new FriendForm());
    }

    @PostMapping("/add-friend")
    public String updateFriends(Model model, @ModelAttribute("friendForm") FriendForm form) {
        User user = sessionService.sessionUser();
        if (userService.checkUser(form.getEmail())){
            linkService.createLink(user, form.getEmail());
            return "redirect:/";
        }
        return "redirect:/add-friend";
    }
}
