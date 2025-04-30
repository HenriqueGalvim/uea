package com.example.projeto.uea.uea.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.uea.uea.dto.AtividadeResponseDTO;
import com.example.projeto.uea.uea.dto.FiltroAtividadeDTO;
import com.example.projeto.uea.uea.model.Atividade;
import com.example.projeto.uea.uea.model.Categoria;
import com.example.projeto.uea.uea.services.AtividadeService;
import com.example.projeto.uea.uea.services.CategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicController {

    private final AtividadeService atividadeService;
    private final CategoriaService categoriaService;

    @GetMapping("/atividades")
    public ResponseEntity<List<AtividadeResponseDTO>> listarPublicadas() {
        List<Atividade> atividades = atividadeService.listarTodasPublicadas();
        List<AtividadeResponseDTO> dtos = atividades.stream()
                .map(atividadeService::mapToDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/atividades/{id}")
    public ResponseEntity<AtividadeResponseDTO> detalhes(@PathVariable Long id) {
        Atividade atividade = atividadeService.buscarPorId(id);
        if (!atividade.isPublicada()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(atividadeService.mapToDTO(atividade));
    }

    @PostMapping("/atividades/filtro")
    public ResponseEntity<List<AtividadeResponseDTO>> filtrarPublicas(@RequestBody FiltroAtividadeDTO filtro) {
        filtro.setPublicada(true);
        List<Atividade> atividades = atividadeService.filtrar(filtro);
        List<AtividadeResponseDTO> dtos = atividades.stream()
                .map(atividadeService::mapToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategoriasPublicas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

}
