package com.example.demo.services;

import com.example.demo.models.Cocina;

import java.util.List;
import java.util.Optional;

public interface CocinaService {
    List<Cocina> findAll();
    Optional<Cocina> findById(Long id);
    Cocina create(Cocina cocina);
    Cocina update(Long id, Cocina cocina);
    void delete(Long id);
}

