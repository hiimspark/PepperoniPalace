package org.example.pizzeria.service;

import lombok.AllArgsConstructor;
import org.example.pizzeria.dto.CartItemDTO;
import org.example.pizzeria.entity.UserEntity;
import org.example.pizzeria.entity.PizzaEntity;
import org.example.pizzeria.entity.CartItemEntity;
import org.example.pizzeria.service.PizzaService;
import org.example.pizzeria.repository.UserRepository;
import org.example.pizzeria.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.ArrayList;


@Service
@AllArgsConstructor
public class CartItemService {
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    public CartItemEntity create(PizzaEntity pizzaE, Principal principal, int quantity){
        if (cartItemRepository.findAll().contains(findCartItem(getUserByPrincipal(principal), pizzaE))){
            int q = findCartItem(getUserByPrincipal(principal), pizzaE).getQuantity();
            CartItemEntity pizza = findCartItem(getUserByPrincipal(principal), pizzaE);
            pizza.setQuantity(q + 1);
            return cartItemRepository.save(pizza);
        }
        CartItemEntity pizza = CartItemEntity.builder()
                .user(getUserByPrincipal(principal))
                .pizza(pizzaE)
                .quantity(quantity)
                .build();
        return cartItemRepository.save(pizza);
    }

    public List<CartItemEntity> readAll() {
        return cartItemRepository.findAll();
    }

    public CartItemEntity findCartItem(UserEntity user, PizzaEntity pizza){
        return cartItemRepository.findByUserAndPizza(user, pizza);
    }

    public UserEntity getUserByPrincipal(Principal principal) {
        if (principal == null) return null;
        return userRepository.findByName(principal.getName()).get();
    }

    public List<CartItemEntity> getUsersCart (Principal principal){
        List<CartItemEntity> fullList = cartItemRepository.findAll();
        List<CartItemEntity> cartList = new ArrayList<CartItemEntity>();
        UserEntity user = getUserByPrincipal(principal);
        for (int i = 0; i < fullList.size(); ++i) {
            if (fullList.get(i).getUser().equals(user))
                cartList.add(fullList.get(i));
        }
        return cartList;
    }

}
