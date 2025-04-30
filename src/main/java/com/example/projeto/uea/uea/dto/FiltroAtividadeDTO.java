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
public class FiltroAtividadeDTO {

    private Long cursoId;
    private Long categoriaId;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Boolean publicada;
}
