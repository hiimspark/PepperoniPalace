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
@Table(name = "pizzas")
@NoArgsConstructor
@AllArgsConstructor
public class PizzaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column
    private String name;
    @Column
    private String price;
    @Column
    private String text;
    @Column
    private byte[] img;

    public String generateBase64Image(){
        return Base64.getEncoder().encodeToString(this.img);
    }

}
