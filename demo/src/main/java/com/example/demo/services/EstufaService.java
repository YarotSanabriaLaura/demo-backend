package com.example.demo.services;

import com.example.demo.models.Estufa;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EstufaService {
    List<Estufa> findAll();
    Optional<Estufa> findById(Long id);
    List<Estufa> findByCocina(Long cocinaId);
    Estufa create(Estufa estufa);                // con cocina.id
    Estufa update(Long id, Estufa estufa);       // con cocina.id
    Estufa patch(Long id, Map<String, Object> fields);
    void delete(Long id);
}
