package com.nnk.springboot.controllers;

import com.nnk.springboot.model.*;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.*;
import com.nnk.springboot.service.form.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
    private final LinkService linkService;
    private final UserService userService;
    private final SessionService sessionService;
    private final FlashCashAccountService flashCashAccountService;

    public UserController(LinkService linkService, UserService userService, SessionService sessionService, FlashCashAccountService flashCashAccountService) {
        this.linkService = linkService;
        this.userService = userService;
        this.sessionService = sessionService;
        this.flashCashAccountService = flashCashAccountService;
    }

    @GetMapping("/")
    public ModelAndView home(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("index");
    }

    @GetMapping("/flash-cash")
    public ModelAndView flashCash(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        List<FlashCashAccount> flashCashAccount = flashCashAccountService.FlashCashInfo();
        model.addAttribute("flashCash", flashCashAccount);
        return new ModelAndView("flashCashAccount");
    }

    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signUpForm", new SignUpForm());
    }

    @PostMapping("/signup")
    public String processRequest(Model model, @ModelAttribute("signUpForm") SignUpForm form)
    {
        if (!userService.isPasswordValid(form.getPassword())){
            model.addAttribute("errorMessageMdp", "Erreur : Mot de Passe invalide.");
            return "signup";
        }
        if (!Objects.equals(form.getPassword(), form.getConfirmPassword())) {
            model.addAttribute("errorMessageMdpConfirm", "Erreur : Mots de Passes non identiques.");
            return "signup";
        } else {
            userService.registration(form);
            return "redirect:/signin";
        }
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
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("account/add-iban", "addIbanForm", new AddIbanForm());
    }

    @PostMapping("add-iban")
    public String addIban(Model model, @ModelAttribute("addIbanForm") AddIbanForm form) {
        User user = sessionService.sessionUser();
        try {
            userService.AddIban(form, user);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("user", user); // Repasser l'utilisateur si nécessaire
            model.addAttribute("errorMessage", "Erreur : IBAN invalide.");
            return "account/add-iban";
        }
    }

    @GetMapping("/depot")
    public ModelAndView getDepotForm(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
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
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("account/retrait", "depotForm", new DepotForm());
    }

    @PostMapping("retrait")
    public String updateRetrait(Model model, @ModelAttribute("depotForm") DepotForm form) {
        User user = sessionService.sessionUser();
        if (userService.checkRetrait(form, user)) {
            userService.PickMoney(form, user);
            return "redirect:/";
        } else {
            model.addAttribute("user", user); // Repasser l'utilisateur si nécessaire
            model.addAttribute("errorMessage", "Erreur : fonds insuffisants.");
            return "account/retrait"; // Retourner la vue directement
        }
    }

    @GetMapping("/friends")
    public ModelAndView friendsList(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("account/friends");
    }

    @GetMapping("/add-friend")
    public ModelAndView getFriendForm(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("link/add-friend", "friendForm", new FriendForm());
    }

    @PostMapping("/add-friend")
    public String updateFriends(Model model, @ModelAttribute("friendForm") FriendForm form) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        if (!userService.checkUser(form.getEmail())){
            model.addAttribute("errorMessage", "Erreur : utilisateur inexistant ou mail invalide.");
            return "link/add-friend";
        }
        else if (Objects.equals(form.getEmail(), user.getEmail())) {
            model.addAttribute("errorMessage", "Erreur : votre mail ne compte pas.");
            return "link/add-friend";
        }
        else if (linkService.checkLink(user, form.getEmail())) {
            model.addAttribute("errorMessage", "Erreur : utilisateur déjà ami.");
            return "link/add-friend";
        }
        else {
            linkService.createLink(user, form.getEmail());
            return "redirect:/";
        }
    }

    @GetMapping("/delete-friend/{id}")
    public String deleteFriend(@PathVariable("id") Integer targetId, Model model) {
        User user = sessionService.sessionUser();
        User target = userService.targeting(targetId);
        linkService.deleteLink(user, target);
        model.addAttribute("user", user);
        return "account/friends";
    }
}
