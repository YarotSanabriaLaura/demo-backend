package com.example.demo.repository;

import com.example.demo.models.Estufa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstufaRepository extends JpaRepository<Estufa, Long> {
    // extra: listar estufas por cocina
    List<Estufa> findByCocinaId(Long cocinaId);
}
