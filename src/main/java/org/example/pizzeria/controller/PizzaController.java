package org.example.pizzeria.controller;

import lombok.AllArgsConstructor;
import org.example.pizzeria.dto.PizzaDTO;
import org.example.pizzeria.entity.UserEntity;
import org.example.pizzeria.entity.PizzaEntity;
import org.example.pizzeria.entity.CartItemEntity;
import org.example.pizzeria.service.PizzaService;
import org.example.pizzeria.service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class PizzaController {
    private final PizzaService pizzaService;
    private final CartItemService cartItemService;

    @RequestMapping(value = "/postman", method = RequestMethod.GET)
    public ResponseEntity<List<PizzaEntity>> readAll() {
        return new ResponseEntity<>(pizzaService.readAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/postman", method = RequestMethod.POST)
    public ResponseEntity<PizzaEntity> create(@RequestBody PizzaDTO dto) {
        return new ResponseEntity<>(pizzaService.create(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/postman", method = RequestMethod.PUT)
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        return new ResponseEntity<>(pizzaService.update(pizza), HttpStatus.OK);
    }

    @DeleteMapping("/postman/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        pizzaService.delete(id);
        return HttpStatus.OK;
    }

}

