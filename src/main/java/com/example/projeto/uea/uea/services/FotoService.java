package com.example.projeto.uea.uea.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.projeto.uea.uea.dto.FotoRequestDTO;
import com.example.projeto.uea.uea.dto.FotoResponseDTO;
import com.example.projeto.uea.uea.model.Atividade;
import com.example.projeto.uea.uea.model.Foto;
import com.example.projeto.uea.uea.repository.AtividadeRepository;
import com.example.projeto.uea.uea.repository.FotoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FotoService {

    private final FotoRepository fotoRepository;
    private final AtividadeRepository atividadeRepository;
    private final CursoService cursoService;

    public Foto adicionarFoto(Foto foto, Long atividadeId) {
        Atividade atividade = atividadeRepository.findById(atividadeId)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

        if (!cursoService.usuarioEhGerenteDoCurso(atividade.getCurso().getId())) {
            throw new RuntimeException("Sem permissão para adicionar fotos nesta atividade.");
        }

        foto.setAtividade(atividade);
        return fotoRepository.save(foto);
    }

    public List<Foto> listarFotosPorAtividade(Long atividadeId) {
        return fotoRepository.findByAtividadeId(atividadeId);
    }

    public FotoResponseDTO mapToDTO(Foto foto) {
        return FotoResponseDTO.builder()
                .id(foto.getId())
                .url(foto.getUrl())
                .legenda(foto.getLegenda())
                .build();
    }

    public Foto mapToEntity(FotoRequestDTO dto) {
        return Foto.builder()
                .url(dto.getUrl())
                .legenda(dto.getLegenda())
                .build();
    }
    public Foto editarLegenda(Long id, String novaLegenda) {
        Foto foto = fotoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Foto não encontrada"));
        foto.setLegenda(novaLegenda);
        return fotoRepository.save(foto);
    }
    
}
