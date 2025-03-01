package com.riosr.sq_ch9.controllers;

import com.riosr.sq_ch9.service.LoginCountService;
import com.riosr.sq_ch9.service.LoginProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final LoginProcessor loginProcessor;
    private final LoginCountService loginCountService;

    public LoginController(LoginProcessor loginProcessor, LoginCountService loginCountService) {
        this.loginProcessor = loginProcessor;
        this.loginCountService = loginCountService;
    }

    @GetMapping("/")
    public String login() {
        return "login.html";
    }

    @PostMapping("/")
    public String loginPost(Model model, @RequestParam String username, @RequestParam String password) {
        loginCountService.increment();

        loginProcessor.setUsername(username);
        loginProcessor.setPassword(password);
        System.out.println(loginProcessor);

        boolean loggedIn = loginProcessor.login();

        if (loggedIn) {
            model.addAttribute("message", "You are now logged in.");
            return "redirect:/main";
        }

        model.addAttribute("message", "Login failed!");
        return "login.html";
    }
}
