package org.example.pizzeria.controller;

import lombok.AllArgsConstructor;
import org.example.pizzeria.entity.PizzaEntity;
import org.example.pizzeria.service.PizzaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MenuController {

    private final PizzaService pizzaService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/menu")
    public String menu(@RequestParam(value="filter", required = false) String filter, Model model){
        Iterable<PizzaEntity> pizzas = pizzaService.readAll();
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
}
