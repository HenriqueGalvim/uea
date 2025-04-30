package com.example.projeto.uea.uea.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.projeto.uea.uea.dto.AtividadeRequestDTO;
import com.example.projeto.uea.uea.dto.AtividadeResponseDTO;
import com.example.projeto.uea.uea.dto.FiltroAtividadeDTO;
import com.example.projeto.uea.uea.dto.FotoResponseDTO;
import com.example.projeto.uea.uea.model.Atividade;
import com.example.projeto.uea.uea.model.Categoria;
import com.example.projeto.uea.uea.model.Curso;
import com.example.projeto.uea.uea.model.Foto;
import com.example.projeto.uea.uea.repository.AtividadeRepository;
import com.example.projeto.uea.uea.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;
    private final CategoriaRepository categoriaRepository;
    private final CursoService cursoService;

    public Atividade criarAtividade(Atividade atividade) {
        if (!cursoService.usuarioEhGerenteDoCurso(atividade.getCurso().getId())) {
            throw new RuntimeException("Você não tem permissão para adicionar atividades a este curso.");
        }
        return atividadeRepository.save(atividade);
    }

    public Atividade editarAtividade(Atividade atividadeAtualizada) {
        Atividade original = atividadeRepository.findById(atividadeAtualizada.getId())
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

        if (!cursoService.usuarioEhGerenteDoCurso(original.getCurso().getId())) {
            throw new RuntimeException("Sem permissão para editar essa atividade.");
        }

        original.setTitulo(atividadeAtualizada.getTitulo());
        original.setDescricao(atividadeAtualizada.getDescricao());
        original.setData(atividadeAtualizada.getData());
        original.setCategoria(atividadeAtualizada.getCategoria());
        original.setPublicoAlvo(atividadeAtualizada.getPublicoAlvo());
        return atividadeRepository.save(original);
    }

    public List<Atividade> listarPorCurso(Long cursoId) {
        return atividadeRepository.findByCursoId(cursoId);
    }

    public void alterarStatusPublicacao(Long id, boolean publicar) {
        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
        atividade.setPublicada(publicar);
        atividadeRepository.save(atividade);
    }

    public AtividadeResponseDTO mapToDTO(Atividade atividade) {
        return AtividadeResponseDTO.builder()
                .id(atividade.getId())
                .titulo(atividade.getTitulo())
                .descricao(atividade.getDescricao())
                .nomeCategoria(atividade.getCategoria().getNome())
                .publicoAlvo(atividade.getPublicoAlvo())
                .data(atividade.getData())
                .publicada(atividade.isPublicada())
                .nomeCurso(atividade.getCurso().getNome())
                .fotos(
                        atividade.getFotos() != null
                                ? atividade.getFotos().stream().map(this::mapFotoToDTO).toList()
                                : List.of())
                .build();
    }

    public FotoResponseDTO mapFotoToDTO(Foto foto) {
        return FotoResponseDTO.builder()
                .id(foto.getId())
                .url(foto.getUrl())
                .legenda(foto.getLegenda())
                .build();
    }

    public Atividade mapToEntity(AtividadeRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return Atividade.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .publicoAlvo(dto.getPublicoAlvo())
                .data(dto.getData())
                .curso(Curso.builder().id(dto.getCursoId()).build())
                .categoria(categoria)
                .build();
    }

    public List<Atividade> listarTodasPublicadas() {
        return atividadeRepository.findAll().stream()
                .filter(Atividade::isPublicada)
                .toList();
    }

    public Atividade buscarPorId(Long id) {
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
    }

    public List<Atividade> filtrar(FiltroAtividadeDTO filtro) {
        return atividadeRepository.findAll().stream()
                .filter(a -> filtro.getCursoId() == null || a.getCurso().getId().equals(filtro.getCursoId()))
                .filter(a -> filtro.getCategoriaId() == null || a.getCategoria().getId().equals(filtro.getCategoriaId()))
                .filter(a -> filtro.getPublicada() == null || a.isPublicada() == filtro.getPublicada())
                .filter(a -> filtro.getDataInicial() == null || !a.getData().isBefore(filtro.getDataInicial()))
                .filter(a -> filtro.getDataFinal() == null || !a.getData().isAfter(filtro.getDataFinal()))
                .toList();
    }
}
