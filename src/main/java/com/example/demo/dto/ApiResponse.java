package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class ApiResponse {
    private Boolean success;
    private String message;

}
