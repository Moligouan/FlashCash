package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Link;
import com.nnk.springboot.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

    @Autowired
    private UserAccountRepository bidListRepository;

//    @RequestMapping("/bidList/list")
//    public String home(Model model)
//    {
//        model.addAttribute("bidList", bidListRepository.findAll());
//        return "bidList/list";
//    }

//    @GetMapping("/bidList/add")
//    public String addBidForm(Link bid) {
//        return "bidList/add";
//    }

//    @PostMapping("/bidList/validate")
//    public String validate(@Valid Link bid, BindingResult result, Model model) {
//        if (!result.hasErrors()) {
//            bidListRepository.save(bid);
//            model.addAttribute("bidList", bidListRepository.findAll());
//            return "redirect:/bidList/list";
//        }
//        return "bidList/add";
//    }

//    @GetMapping("/bidList/update/{id}")
//    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
//        Link bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
//        model.addAttribute("bidList", bidList);
//        return "bidList/update";
//    }

//    @PostMapping("/bidList/update/{id}")
//    public String updateBid(@PathVariable("id") Integer id, @Valid Link bidList,
//                             BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "bidList/update";
//        }
//        bidList.setBidListId(id);
//        bidListRepository.save(bidList);
//        model.addAttribute("bidList", bidListRepository.findAll());
//        return "redirect:/bidList/list";
//    }

//    @GetMapping("/bidList/delete/{id}")
//    public String deleteBid(@PathVariable("id") Integer id, Model model) {
//        Link bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
//        bidListRepository.delete(bidList);
//        model.addAttribute("bidList", bidListRepository.findAll());
//        return "redirect:/bidList/list";
//    }
}