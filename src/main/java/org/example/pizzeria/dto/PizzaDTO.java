package org.example.pizzeria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PizzaDTO {
    private String name;
    private String price;
    private String text;
    private byte[] img;
}
