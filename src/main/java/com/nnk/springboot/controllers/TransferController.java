package com.nnk.springboot.controllers;

import com.nnk.springboot.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TransferController {
//
//    @RequestMapping("/curvePoint/list")
//    public String home(Model model)
//    {
//        model.addAttribute("curvePoints", curvePointRepository.findAll());
//        return "curvePoint/list";
//    }
//
//    @GetMapping("/curvePoint/add")
//    public String addCurveForm(UserAccount curvePoint) {
//        return "curvePoint/add";
//    }

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
