package com.riosr.sq_ch7.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class MainController {

    @RequestMapping("/")
    public String home() {
        return "index.html";
    }
}
