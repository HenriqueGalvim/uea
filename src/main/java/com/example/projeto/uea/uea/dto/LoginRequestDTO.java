package com.example.projeto.uea.uea.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotNull(message = "O email é obrigatório.")
    String username;
    @NotNull(message = "A senha é obrigatória.")
    String password;
}