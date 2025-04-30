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

import com.example.projeto.uea.uea.dto.EditarLegendaFotoDTO;
import com.example.projeto.uea.uea.dto.FotoRequestDTO;
import com.example.projeto.uea.uea.dto.FotoResponseDTO;
import com.example.projeto.uea.uea.model.Foto;
import com.example.projeto.uea.uea.services.FotoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/fotos")
@RequiredArgsConstructor
public class FotoController {

    private final FotoService fotoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('GERENTE', 'SECRETARIO')")
    public ResponseEntity<FotoResponseDTO> adicionar(@RequestBody @Valid FotoRequestDTO dto) {
        Foto foto = fotoService.mapToEntity(dto); // torná-lo public no service
        Foto salva = fotoService.adicionarFoto(foto, dto.getAtividadeId());
        return ResponseEntity.status(HttpStatus.CREATED).body(fotoService.mapToDTO(salva));
    }

    @GetMapping("/atividade/{atividadeId}")
    @PreAuthorize("hasAnyRole('GERENTE', 'SECRETARIO')")
    public ResponseEntity<List<FotoResponseDTO>> listar(@PathVariable Long atividadeId) {
        List<Foto> fotos = fotoService.listarFotosPorAtividade(atividadeId);
        List<FotoResponseDTO> dtos = fotos.stream()
                .map(fotoService::mapToDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'SECRETARIO')")
    public ResponseEntity<FotoResponseDTO> editarLegenda(
            @PathVariable Long id,
            @RequestBody EditarLegendaFotoDTO dto) {

        Foto atualizada = fotoService.editarLegenda(id, dto.getNovaLegenda());
        return ResponseEntity.ok(fotoService.mapToDTO(atualizada));
    }

}
