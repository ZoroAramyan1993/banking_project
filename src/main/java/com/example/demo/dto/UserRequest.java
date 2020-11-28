package com.example.demo.dto;

import com.example.demo.entity.Card;
import com.example.demo.entity.Transaction;
import com.example.demo.enums.RoleName;
import com.sun.istack.NotNull;
import lombok.*;


import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class UserRequest {

    @NotNull
    private String name;

    @NotNull
    private String surName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    RoleName roleName;

}
