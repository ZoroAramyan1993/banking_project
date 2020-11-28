package com.example.demo.dto;


import com.sun.istack.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class LoginRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
