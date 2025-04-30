package com.example.projeto.uea.uea.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.projeto.uea.uea.dto.CursoRequestDTO;
import com.example.projeto.uea.uea.dto.CursoResponseDTO;
import com.example.projeto.uea.uea.model.Curso;
import com.example.projeto.uea.uea.model.Usuario;
import com.example.projeto.uea.uea.repository.CursoRepository;
import com.example.projeto.uea.uea.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    public Curso criarCurso(CursoRequestDTO dto) {
        Usuario gerente = usuarioRepository.findById(dto.getGerenteId())
                .orElseThrow(() -> new RuntimeException("Gerente não encontrado."));

        Curso curso = Curso.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .gerenteResponsavel(gerente)
                .build();

        return cursoRepository.save(curso);
    }

    public Curso atualizarCurso(Long id, CursoRequestDTO dto) {
        Curso curso = buscarPorId(id);

        Usuario gerente = usuarioRepository.findById(dto.getGerenteId())
                .orElseThrow(() -> new RuntimeException("Gerente não encontrado."));

        curso.setNome(dto.getNome());
        curso.setDescricao(dto.getDescricao());
        curso.setGerenteResponsavel(gerente);

        return cursoRepository.save(curso);
    }

    public void deletarCurso(Long id) {
        Curso curso = buscarPorId(id);
        cursoRepository.delete(curso);
    }

    public boolean usuarioEhGerenteDoCurso(Long cursoId) {
        Curso curso = buscarPorId(cursoId);
        return curso.getGerenteResponsavel().getId()
                .equals(usuarioService.getUsuarioLogado().getId());
    }

    public CursoResponseDTO mapToDTO(Curso curso) {
        return CursoResponseDTO.builder()
                .id(curso.getId())
                .nome(curso.getNome())
                .descricao(curso.getDescricao())
                .nomeGerente(curso.getGerenteResponsavel() != null ? curso.getGerenteResponsavel().getUsername() : null)
                .build();
    }
}