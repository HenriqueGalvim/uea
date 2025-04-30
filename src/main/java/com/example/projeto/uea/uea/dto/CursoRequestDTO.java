package com.example.projeto.uea.uea.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoRequestDTO {

    @NotBlank(message = "O nome do curso é obrigatório.")
    private String nome;

    @NotBlank(message = "A descrição do curso é obrigatória.")
    private String descricao;

    @NotNull(message = "O ID do gerente é obrigatório.")
    private Long gerenteId; // ID do usuário com ROLE_GERENTE
}