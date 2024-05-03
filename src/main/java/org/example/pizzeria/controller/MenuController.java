package org.example.pizzeria.controller;

import lombok.AllArgsConstructor;
import org.example.pizzeria.entity.PizzaEntity;
import org.example.pizzeria.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MenuController {

    private final PizzaService pizzaService;

    @GetMapping("/menu")
    public String menu(Model model) {
        Iterable<PizzaEntity> pizzas = pizzaService.readAll();
        model.addAttribute("pizzas", pizzas);
        return "menu";
    }
}
