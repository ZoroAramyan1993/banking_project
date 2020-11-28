package com.example.demo.security;//package com.example.demo.security;

//import com.example.demo.entity.User;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.security.UserPrincipal;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.ResourceAccessException;

import javax.transaction.Transactional;

//@Service
//public class UsDetailsService implements UserDetailsService {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email).orElseThrow(() ->
//                new ResourceAccessException("resource width name email nto found" + email));
//        return UserPrincipal.createUser(user);
//    }
//
//    @Transactional
//    public UserDetails loadUserById(Integer id) {
//        User user = userRepository.findById(id).orElseThrow(() ->
//                new ResourceAccessException("resource width id not found" + id));
//        return UserPrincipal.createUser(user);
//    }
//}
