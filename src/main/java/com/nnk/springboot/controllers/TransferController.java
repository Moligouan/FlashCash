package com.nnk.springboot.controllers;

import com.nnk.springboot.model.User;
import com.nnk.springboot.model.UserAccount;
import com.nnk.springboot.service.SessionService;
import com.nnk.springboot.service.TransferService;
import com.nnk.springboot.service.UserService;
import com.nnk.springboot.service.form.DepotForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        model.addAttribute("targetId", targetId);
        return new ModelAndView("transfer", "depotForm", new DepotForm());
    }

    @PostMapping("/transfer/{id}")
    public String updateDepot(Model model, @PathVariable("id") Integer targetId, @ModelAttribute("depotForm") DepotForm form) {
        User user = sessionService.sessionUser();
        userService.transfer(form, user, targetId);
        transferService.registerTransfer(form, user, targetId);
        return "redirect:/";
    }

//    @PostMapping("/curvePoint/validate")
//    public String validate(@Valid UserAccount curvePoint, BindingResult result, Model model) {
//        if (!result.hasErrors()) {
//            curvePointRepository.save(curvePoint);
//            model.addAttribute("curvePoints", curvePointRepository.findAll());
//            return "redirect:/curvePoint/list";
//        }
//        return "curvePoint/add";
//    }

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
