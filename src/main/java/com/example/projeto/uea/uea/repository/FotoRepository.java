package com.example.projeto.uea.uea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto.uea.uea.model.Foto;

public interface FotoRepository extends JpaRepository<Foto, Long> {
    List<Foto> findByAtividadeId(Long atividadeId);
}