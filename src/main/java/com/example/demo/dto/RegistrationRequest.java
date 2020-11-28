package com.example.demo.dto;


import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class RegistrationRequest {

    @NotNull
    String name;
    @NotNull
    String surName;

    @NotNull
    String email;

    @NotNull
    String password;

}
