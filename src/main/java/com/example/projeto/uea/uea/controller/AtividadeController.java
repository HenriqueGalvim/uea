package com.example.projeto.uea.uea.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.uea.uea.dto.AtividadeRequestDTO;
import com.example.projeto.uea.uea.dto.AtividadeResponseDTO;
import com.example.projeto.uea.uea.dto.FiltroAtividadeDTO;
import com.example.projeto.uea.uea.model.Atividade;
import com.example.projeto.uea.uea.services.AtividadeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/atividades")
@RequiredArgsConstructor
public class AtividadeController {

    private final AtividadeService atividadeService;

    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<AtividadeResponseDTO> criar(@RequestBody @Valid AtividadeRequestDTO dto) {
        Atividade atividade = atividadeService.criarAtividade(
                atividadeService.mapToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(atividadeService.mapToDTO(atividade));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<AtividadeResponseDTO> editar(@PathVariable Long id,
            @RequestBody @Valid AtividadeRequestDTO dto) {
        Atividade atividade = atividadeService.mapToEntity(dto);
        atividade.setId(id);
        Atividade atualizada = atividadeService.editarAtividade(atividade);
        return ResponseEntity.ok(atividadeService.mapToDTO(atualizada));
    }

    @PutMapping("/{id}/publicar")
    @PreAuthorize("hasAnyRole('GERENTE', 'ADMIN')")
    public ResponseEntity<Void> publicar(@PathVariable Long id) {
        atividadeService.alterarStatusPublicacao(id, true);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/ocultar")
    @PreAuthorize("hasAnyRole('GERENTE', 'ADMIN')")
    public ResponseEntity<Void> ocultar(@PathVariable Long id) {
        atividadeService.alterarStatusPublicacao(id, false);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/curso/{id}")
    @PreAuthorize("hasAnyRole('GERENTE', 'SECRETARIO')")
    public ResponseEntity<List<AtividadeResponseDTO>> listarPorCurso(@PathVariable Long id) {
        List<Atividade> atividades = atividadeService.listarPorCurso(id);
        List<AtividadeResponseDTO> dtos = atividades.stream()
                .map(atividadeService::mapToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/filtro")
    @PreAuthorize("hasAnyRole('GERENTE', 'SECRETARIO')")
    public ResponseEntity<List<AtividadeResponseDTO>> filtrar(@RequestBody FiltroAtividadeDTO filtro) {
        List<Atividade> atividades = atividadeService.filtrar(filtro);
        List<AtividadeResponseDTO> dtos = atividades.stream()
                .map(atividadeService::mapToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

}
