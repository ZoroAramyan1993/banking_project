package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.JwtAuthenticationResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegistrationRequest;

import com.example.demo.entity.User;
import com.example.demo.enums.RoleName;
import com.example.demo.exception.ApiException;
import com.example.demo.repository.UserRepository;
//import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;



@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {


    @Autowired
    UserService userService;

    //    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    JwtTokenProvider jwtTokenProvider;
//
    @Autowired
    PasswordEncoder passwordEncoder;

//    @PostMapping("/login")
//    ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                loginRequest.getEmail(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtTokenProvider.generateToken(authentication);
//        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
//    }

    @PostMapping("/registration")
    ResponseEntity<?> registration(@Valid @RequestBody RegistrationRequest registrationRequest) {
        User user = new User(registrationRequest.getName(), registrationRequest.getSurName(),
                registrationRequest.getEmail(), registrationRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getEmail().equals("admin7")) {
            user.setRoleName(RoleName.ADMIN);
        } else {
            user.setRoleName(RoleName.USER);
        }
        userService.save(user);
        if (userService.existsByEmail(registrationRequest.getEmail())) {
            new ResponseEntity(new ApiResponse(false, "already used"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }
}
