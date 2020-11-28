package com.example.demo.service.impl;

import com.example.demo.entity.Card;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email).
                orElseThrow(() -> new ResourceNotFoundException("not found"));
        return user;
    }

    @Override
    public User save(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            logger.error("user can not be saved");
            throw new ResourceNotFoundException("email already in use.");
        }
        if (user.getRoleName() == null || user.getEmail() == null || user.getName() == null) {
            logger.error("user can not be saved");
            throw new ResourceNotFoundException("not found");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User delete(Integer id) {
        User user = findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        userRepository.deleteById(id);
        return user;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
}
