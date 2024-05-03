package org.example.pizzeria.service;

import lombok.AllArgsConstructor;
import org.example.pizzeria.dto.UserDTO;
import org.example.pizzeria.entity.UserEntity;
import org.example.pizzeria.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity addUser(UserDTO dto){
        UserEntity pizza = UserEntity.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .roles(dto.getRoles())
                .build();
        return userRepository.save(pizza);
    }
    public List<UserEntity> readAll() {
        return userRepository.findAll();
    }

    public UserEntity update(UserEntity product) {
        return userRepository.save(product);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
