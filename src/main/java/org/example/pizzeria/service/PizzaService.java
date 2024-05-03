package org.example.pizzeria.service;

import lombok.AllArgsConstructor;
import org.example.pizzeria.dto.PizzaDTO;
import org.example.pizzeria.entity.PizzaEntity;
import org.example.pizzeria.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    public PizzaEntity create(PizzaDTO dto){
        PizzaEntity pizza = PizzaEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .text(dto.getText())
                .img(dto.getImg())
                .build();
        return pizzaRepository.save(pizza);
    }
    public List<PizzaEntity> readAll() {
        return pizzaRepository.findAll();
    }

    public PizzaEntity update(PizzaEntity product) {
        return pizzaRepository.save(product);
    }

    public void delete(Long id) {
        pizzaRepository.deleteById(id);
    }
}
