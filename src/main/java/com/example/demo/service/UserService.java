package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;
import java.util.Optional;


public interface UserService {
    Optional<User> findById(Integer id);

    User findByEmail(String email);

    User save(User user);

    List<User> findAll();

    User delete(Integer id);

    boolean existsByEmail(String email);

    User update(User user);
}
