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
public class FotoResponseDTO {

    private Long id;
    private String url;
    private String legenda;
}
