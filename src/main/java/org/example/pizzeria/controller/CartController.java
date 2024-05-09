package org.example.pizzeria.controllers;

import lombok.AllArgsConstructor;
import org.example.pizzeria.entity.UserEntity;
import org.example.pizzeria.entity.PizzaEntity;
import org.example.pizzeria.entity.CartItemEntity;
import org.example.pizzeria.service.CartItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMINROLE','USERROLE')")
@AllArgsConstructor
public class CartController {

    private final CartItemService cartItemService;

    @GetMapping("/cart")
    public String cart(Model model, Principal principal) {
        List<CartItemEntity> items = cartItemService.getUsersCart(principal);
        model.addAttribute("items", items);
        return "cart";
    }


}