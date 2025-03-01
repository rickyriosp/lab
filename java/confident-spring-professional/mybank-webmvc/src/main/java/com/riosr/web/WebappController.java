package com.riosr.web;

import com.riosr.service.TransactionService;
import com.riosr.web.forms.TxForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebappController {

    private TransactionService transactionService;

    @Value("${bank.slogan}")
    private String slogan;

    public WebappController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("slogan", slogan);
        return "index.html";
    }

    @GetMapping("/account/{userId}")
    public String account(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("transactions", transactionService.findByReceivingUserId(userId));
        model.addAttribute("txForm", new TxForm());
        return "account.html";
    }

    @PostMapping("/account/{userId}")
    public String createTransaction(@ModelAttribute("txForm") @Valid TxForm txForm,
                                    BindingResult bindingResult,
                                    @PathVariable("userId") String userId,
                                    Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("transactions", transactionService.findByReceivingUserId(userId));

        if (bindingResult.hasErrors()) {
            model.addAttribute("txError", true);
            return "account.html";
        }

        transactionService.create(txForm.getAmount(), txForm.getReference(), txForm.getReceivingUser());
        return "redirect:/account/" + userId;
    }
}
