package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Transfer;
import com.nnk.springboot.model.User;
import com.nnk.springboot.model.UserAccount;
import com.nnk.springboot.service.SessionService;
import com.nnk.springboot.service.TransferService;
import com.nnk.springboot.service.UserService;
import com.nnk.springboot.service.form.DepotForm;
import com.nnk.springboot.service.form.FriendForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TransferController {
    private TransferService transferService;
    private UserService userService;
    private SessionService sessionService;

    public TransferController(TransferService transferService, UserService userService, SessionService sessionService) {
        this.transferService = transferService;
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping("/transfer/{id}")
    public ModelAndView getTransferForm(Model model, @PathVariable("id") Integer targetId) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        User target = userService.targeting(targetId);
        model.addAttribute("target", target);
        return new ModelAndView("transfer", "depotForm", new DepotForm());
    }

    @PostMapping("/transfer/{id}")
    public String updateDepot(Model model, @PathVariable("id") Integer targetId, @ModelAttribute("depotForm") DepotForm form) {
        User user = sessionService.sessionUser();
        User target = userService.targeting(targetId);
        if (userService.checkRetrait(form, user)) {
            userService.transfer(form, user, targetId);
            transferService.registerTransfer(form, user, targetId);
            return "redirect:/";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("target", target);// Repasser l'utilisateur si nécessaire
            model.addAttribute("errorMessage", "Erreur : fonds insuffisants.");
            return "transfer"; // Retourner la vue directement
        }
    }

    @GetMapping("/historique")
    public ModelAndView getHistorique(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        List<Transfer> historique = transferService.historique(user);
        model.addAttribute("historiques", historique);
        return new ModelAndView("account/historique");
    }

//    @GetMapping("/curvePoint/update/{id}")
//    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
//        UserAccount curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
//        model.addAttribute("curvePoint", curvePoint);
//        return "curvePoint/update";
//    }

//    @PostMapping("/curvePoint/update/{id}")
//    public String updateBid(@PathVariable("id") Integer id, @Valid UserAccount curvePoint,
//                             BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "curvePoint/update";
//        }
//        curvePoint.setId(id);
//        curvePointRepository.save(curvePoint);
//        model.addAttribute("curvePoint", curvePointRepository.findAll());
//        return "redirect:/curvePoint/list";
//    }

//    @GetMapping("/curvePoint/delete/{id}")
//    public String deleteBid(@PathVariable("id") Integer id, Model model) {
//        UserAccount curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
//        curvePointRepository.delete(curvePoint);
//        model.addAttribute("curvePoint", curvePointRepository.findAll());
//        return "redirect:/curvePoint/list";
//    }
}
