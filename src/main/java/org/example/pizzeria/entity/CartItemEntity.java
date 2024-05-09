package org.example.pizzeria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Entity
@Data
@Builder
@Table(name = "cart_items")
@NoArgsConstructor
@AllArgsConstructor
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "pizza_id", referencedColumnName = "id")
    private PizzaEntity pizza;

    public String generateBase64Image(){
        return Base64.getEncoder().encodeToString(pizza.getImg());
    }

    private int quantity;
}
