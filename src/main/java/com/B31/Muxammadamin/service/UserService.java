package com.B31.Muxammadamin.service;

import com.B31.Muxammadamin.entity.Debt;
import com.B31.Muxammadamin.entity.User;
import com.B31.Muxammadamin.repository.DebtRepository;
import com.B31.Muxammadamin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final DebtRepository debtRepository;

    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Invalid User credentials!");
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<Debt> findUserDebts(int userId) {
        return debtRepository.findByUserId(userId);
    }

    public void register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }
        userRepository.save(user);
    }
}
