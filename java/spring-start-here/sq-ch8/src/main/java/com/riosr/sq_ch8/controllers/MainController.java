package com.riosr.sq_ch8.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MainController {

    @RequestMapping("/home/{name}")
    public String home(Model model, @PathVariable String name, @RequestParam Optional<String> color) {
        System.out.println("name: " + name + ", color: " + color);
        model.addAttribute("username", name);
        model.addAttribute("color", color.orElse("red"));
        return "home.html";
    }
}
