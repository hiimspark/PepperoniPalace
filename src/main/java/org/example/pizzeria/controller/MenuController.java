package org.example.pizzeria.controller;

import lombok.AllArgsConstructor;
import org.example.pizzeria.entity.PizzaEntity;
import org.example.pizzeria.service.PizzaService;
import org.example.pizzeria.service.CartItemService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


@Controller
@AllArgsConstructor
public class MenuController {

    private final PizzaService pizzaService;
    private final CartItemService cartItemService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/menu")
    public String menu(@RequestParam(value="filter", required = false) String filter, @RequestParam(value="pizzaN", required = false) String pizzaN,
    Principal principal, Model model){
        List<PizzaEntity> pizzas = pizzaService.readAll();
        if (pizzaN != null){
            if (principal != null){
                PizzaEntity pizza = findPizzaByName(pizzaN, pizzas);
                cartItemService.create(pizza, principal, 1);
            }
        }
        if (filter != null) {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<PizzaEntity> pizzaCriteriaQuery = builder.createQuery(PizzaEntity.class);
            Root<PizzaEntity> root = pizzaCriteriaQuery.from(PizzaEntity.class);
            switch(filter){
                case "asc":
                {
                    pizzaCriteriaQuery.select(root).orderBy(builder.asc(root.get("price")));
                    break;
                }
                case "desc":{
                    pizzaCriteriaQuery.select(root).orderBy(builder.desc(root.get("price")));
                    break;
                }
                case "abc":{
                    pizzaCriteriaQuery.select(root).orderBy(builder.asc(root.get("name")));
                    break;
                }
                case "cba":{
                    pizzaCriteriaQuery.select(root).orderBy(builder.desc(root.get("name")));
                    break;
                }
            }
            Query<PizzaEntity> query = (Query<PizzaEntity>) entityManager.createQuery(pizzaCriteriaQuery);
            pizzas = query.getResultList();
            model.addAttribute("pizzas", pizzas);
        }
        else{
            model.addAttribute("pizzas", pizzas);
        }
        return "menu";
    }

    public PizzaEntity findPizzaByName(String name, List<PizzaEntity> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getName().equals(name))
                return list.get(i);
        }
        return null;
    }
}
