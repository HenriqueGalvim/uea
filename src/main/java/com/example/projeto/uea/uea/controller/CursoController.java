package com.example.projeto.uea.uea.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.uea.uea.dto.CursoRequestDTO;
import com.example.projeto.uea.uea.dto.CursoResponseDTO;
import com.example.projeto.uea.uea.model.Curso;
import com.example.projeto.uea.uea.services.CursoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CursoResponseDTO> criarCurso(@RequestBody @Valid CursoRequestDTO dto) {
        Curso curso = cursoService.criarCurso(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(curso));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public ResponseEntity<List<CursoResponseDTO>> listarCursos() {
        List<Curso> cursos = cursoService.listarTodos();
        List<CursoResponseDTO> dtos = cursos.stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public ResponseEntity<CursoResponseDTO> buscarPorId(@PathVariable Long id) {
        Curso curso = cursoService.buscarPorId(id);
        return ResponseEntity.ok(mapToDTO(curso));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CursoResponseDTO> atualizarCurso(@PathVariable Long id, @RequestBody @Valid CursoRequestDTO dto) {
        Curso curso = cursoService.atualizarCurso(id, dto);
        return ResponseEntity.ok(mapToDTO(curso));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        cursoService.deletarCurso(id);
        return ResponseEntity.noContent().build();
    }

    private CursoResponseDTO mapToDTO(Curso curso) {
        return CursoResponseDTO.builder()
            .id(curso.getId())
            .nome(curso.getNome())
            .descricao(curso.getDescricao())
            .nomeGerente(curso.getGerenteResponsavel() != null ? curso.getGerenteResponsavel().getUsername() : null)
            .build();
    }
}

