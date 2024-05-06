package org.example.pizzeria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegController {
    @GetMapping("/reg")
    public String reg(Model model) {
        return "reg";
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}