package com.example.projeto.uea.uea.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.projeto.uea.uea.model.Categoria;
import com.example.projeto.uea.uea.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria nova) {
        Categoria existente = buscarPorId(id);
        existente.setNome(nova.getNome());
        return categoriaRepository.save(existente);
    }

    public void deletar(Long id) {
        Categoria categoria = buscarPorId(id);
        categoriaRepository.delete(categoria);
    }
}
