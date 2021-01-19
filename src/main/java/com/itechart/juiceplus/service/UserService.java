package com.itechart.juiceplus.service;

import com.itechart.juiceplus.entity.User;
import com.itechart.juiceplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User id:%s not found.", id)));
    }
}
