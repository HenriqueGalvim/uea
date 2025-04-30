package com.example.projeto.uea.uea.dto;

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
public class CursoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String nomeGerente; // do usuário vinculado
}
