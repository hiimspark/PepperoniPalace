package org.example.pizzeria.controller;

import lombok.AllArgsConstructor;
import org.example.pizzeria.dto.PizzaDTO;
import org.example.pizzeria.entity.PizzaEntity;
import org.example.pizzeria.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Base64;

@Controller
@AllArgsConstructor
public class AdminPageController {
    private final PizzaService pizzaService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String addMenu(@RequestParam(value="ask_name", required = false) String pizzaN, Model model) {
        List<PizzaEntity> pizzas = pizzaService.readAll();
        if (pizzaN != null){
            long pizzaId = -1;
            for (int i = 0; i < pizzas.size(); ++i) {
                if (pizzas.get(i).getName().equals(pizzaN)){
                    pizzaId = i;
                    break;
                }
            }
            if (pizzaId == -1){
                model.addAttribute("findResult", "Пицца не найдена :(");
            }
            else{
                model.addAttribute("findResult", "ID пиццы: " + pizzaId);
            }
        }
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity<PizzaEntity> addPizza(@RequestParam String name, @RequestParam String price,
                                                @RequestParam String text, @RequestParam String imgBase64, Model model) {
        byte[] img = Base64.getDecoder().decode(imgBase64);
        PizzaDTO dto = new PizzaDTO(name, price, text, img);
        return new ResponseEntity<>(pizzaService.create(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id, Model model) {
        pizzaService.delete(id);
    }
}
