package com.example.demo.services;

import com.example.demo.models.Cocina;
import com.example.demo.repository.CocinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CocinaServiceImpl implements CocinaService {

    private final CocinaRepository repo;

    public CocinaServiceImpl(CocinaRepository repo) { this.repo = repo; }

    @Override public List<Cocina> findAll() { return repo.findAll(); }

    @Override public Optional<Cocina> findById(Long id) { return repo.findById(id); }

    @Override public Cocina create(Cocina cocina) { cocina.setId(null); return repo.save(cocina); }

    @Override
    public Cocina update(Long id, Cocina cocina) {
        Cocina db = repo.findById(id).orElseThrow(() -> new NoSuchElementException("No existe cocina " + id));
        db.setNombre(cocina.getNombre());
        db.setUbicacion(cocina.getUbicacion());
        return repo.save(db);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException("No existe cocina " + id);
        repo.deleteById(id);
    }
}
