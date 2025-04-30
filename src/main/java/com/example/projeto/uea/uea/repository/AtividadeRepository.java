package com.example.projeto.uea.uea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto.uea.uea.model.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    List<Atividade> findByCursoId(Long cursoId);
}
