package org.example.pizzeria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDTO {
    private Long userId;
    private Long pizzaId;
    private int quantity;
}
