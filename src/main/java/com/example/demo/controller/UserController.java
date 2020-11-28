package com.example.demo.controller;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/{id}")
    ResponseEntity<User> get(@PathVariable Integer id) {
        User user = userService.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("not found"));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get/{email}")
    ResponseEntity<User> get(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.findAll();
    }

    @Transactional
    @PutMapping("/{userId}/role")
    ResponseEntity updateUser(@Valid @RequestBody UserRequest userRequest, @PathVariable Integer userId) {
        User user = userService.findById(userId).map(user1 -> {
            user1.setRoleName(userRequest.getRoleName());
            return userService.update(user1);
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete")
    ResponseEntity deleteUser(@RequestParam("id") Integer id) {
        User user = userService.delete(id);
        return ResponseEntity.ok(user);
    }

}
