package com.Tocloc.Tocloc.service;

import com.Tocloc.Tocloc.dao.UserRepository;
import com.Tocloc.Tocloc.entities.User;
import com.Tocloc.Tocloc.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + userId + " não foi encontrado"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, User userDetails) {
        User userExistente = findById(userId);

        // Atualizar os dados do usuário existente
        userExistente.setName(userDetails.getName());
        userExistente.setEmail(userDetails.getEmail());
        userExistente.setPassword(userDetails.getPassword());
        userExistente.setPhoneNumber(userDetails.getPhoneNumber());
        userExistente.setTypeOfUser(userDetails.getTypeOfUser());

        return userRepository.save(userExistente);
    }

    @Override
    public void deleteById(Long userId) {
        findById(userId);
        userRepository.deleteById(userId);
    }
}
