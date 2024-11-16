package com.Tocloc.Tocloc.service;

import com.Tocloc.Tocloc.dao.UserRepository;
import com.Tocloc.Tocloc.entities.User.User;
import com.Tocloc.Tocloc.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
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
    public void deleteById(Long userId) {
        User user = findById(userId);
        userRepository.delete(user);
    }
    @Override
    public User update(Long userId, User userDetails) {
        User existingUser = findById(userId);
        existingUser.setName(userDetails.getName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPhoneNumber(userDetails.getPhoneNumber());
        existingUser.setTypeOfUser(userDetails.getTypeOfUser());
        return userRepository.save(existingUser);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário com email " + email + " não foi encontrado"));
    }

}
