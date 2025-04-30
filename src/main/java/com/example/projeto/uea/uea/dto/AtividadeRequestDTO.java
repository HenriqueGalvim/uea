package com.example.projeto.uea.uea.dto;

import java.time.LocalDate;

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
public class AtividadeRequestDTO {

    private String titulo;
    private String descricao;
    private String publicoAlvo;
    private LocalDate data;
    private Long cursoId;
    private Long categoriaId;
}