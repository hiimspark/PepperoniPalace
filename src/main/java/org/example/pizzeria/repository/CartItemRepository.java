package org.example.pizzeria.repository;

import org.example.pizzeria.entity.UserEntity;
import org.example.pizzeria.entity.PizzaEntity;
import org.example.pizzeria.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long>{
    CartItemEntity findByUserAndPizza(UserEntity user, PizzaEntity pizza);
}
