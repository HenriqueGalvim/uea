package com.example.projeto.uea.uea.dto;

import java.time.LocalDate;
import java.util.List;

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
public class AtividadeResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String publicoAlvo;
    private String nomeCategoria;
    private LocalDate data;
    private boolean publicada;
    private String nomeCurso;
    private List<FotoResponseDTO> fotos;
}
