package com.example.demo.security;
import com.example.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


//public class UserPrincipal implements UserDetails {
//
//    private Integer id;
//    private String name;
//    private String surName;
//    private String email;
//    private String password;
//    private Collection<? extends GrantedAuthority>authorities;
//
//    public UserPrincipal(Integer id, String name, String surName, String email,
//                         String password, Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.name = name;
//        this.surName = surName;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//  public static UserPrincipal createUser(User user){
////    List<GrantedAuthority> authorities = user.getRoleName().stream().map(role->
////            new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
//     List<GrantedAuthority> authority =
//             Collections.singletonList(new SimpleGrantedAuthority(user.getRoleName().name()));
//    return new UserPrincipal(user.getId(),
//            user.getName(),user.getSurName(),user.getEmail(),user.getPassword(),  authority);
//  }
//
//    public Integer getId() {
//        return id;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//       return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof UserPrincipal)) return false;
//        UserPrincipal userPrincipal = (UserPrincipal) o;
//        return Objects.equals(getId(), userPrincipal.getId()) &&
//                Objects.equals(name, userPrincipal.name) &&
//                Objects.equals(surName, userPrincipal.surName) &&
//                Objects.equals(email, userPrincipal.email) &&
//                Objects.equals(getPassword(), userPrincipal.getPassword()) &&
//                Objects.equals(getAuthorities(), userPrincipal.getAuthorities());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), name, surName, email, getPassword(), getAuthorities());
//    }
//}
