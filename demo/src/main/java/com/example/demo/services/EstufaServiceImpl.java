package com.example.demo.services;

import com.example.demo.models.Cocina;
import com.example.demo.models.Estufa;
import com.example.demo.repository.CocinaRepository;
import com.example.demo.repository.EstufaRepository;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class EstufaServiceImpl implements EstufaService {

    private final EstufaRepository repo;
    private final CocinaRepository cocinaRepo;

    public EstufaServiceImpl(EstufaRepository repo, CocinaRepository cocinaRepo) {
        this.repo = repo;
        this.cocinaRepo = cocinaRepo;
    }

    @Override public List<Estufa> findAll() { return repo.findAll(); }

    @Override public Optional<Estufa> findById(Long id) { return repo.findById(id); }

    @Override public List<Estufa> findByCocina(Long cocinaId) { return repo.findByCocinaId(cocinaId); }

    @Override
    public Estufa create(Estufa estufa) {
        estufa.setId(null);
        // Resolver cocina por id si viene { "cocina": { "id": X } }
        if (estufa.getCocina() != null && estufa.getCocina().getId() != null) {
            Cocina c = cocinaRepo.findById(estufa.getCocina().getId())
                    .orElseThrow(() -> new NoSuchElementException("Cocina no encontrada"));
            estufa.setCocina(c);
        } else {
            estufa.setCocina(null); // opcional si el ingeniero permite nulas
        }
        return repo.save(estufa);
    }

    @Override
    public Estufa update(Long id, Estufa estufa) {
        Estufa db = repo.findById(id).orElseThrow(() -> new NoSuchElementException("No existe estufa " + id));
        db.setMarca(estufa.getMarca());
        db.setModelo(estufa.getModelo());
        db.setTipo(estufa.getTipo());
        db.setPotencia(estufa.getPotencia());
        db.setDescripcion(estufa.getDescripcion());

        if (estufa.getCocina() != null && estufa.getCocina().getId() != null) {
            Cocina c = cocinaRepo.findById(estufa.getCocina().getId())
                    .orElseThrow(() -> new NoSuchElementException("Cocina no encontrada"));
            db.setCocina(c);
        } else {
            db.setCocina(null);
        }
        return repo.save(db);
    }

    @Override
    public Estufa patch(Long id, Map<String, Object> fields) {
        Estufa db = repo.findById(id).orElseThrow(() -> new NoSuchElementException("No existe estufa " + id));
        fields.remove("id"); // nunca modificar id

        // Si viene cocina como { cocina: { id: X } } lo resolvemos manual
        if (fields.containsKey("cocina")) {
            Object cocinaObj = fields.remove("cocina");
            if (cocinaObj instanceof Map<?,?> map && map.get("id") != null) {
                Long cocinaId = Long.valueOf(map.get("id").toString());
                Cocina c = cocinaRepo.findById(cocinaId)
                        .orElseThrow(() -> new NoSuchElementException("Cocina no encontrada"));
                db.setCocina(c);
            } else {
                db.setCocina(null);
            }
        }

        BeanWrapper w = new BeanWrapperImpl(db);
        fields.forEach((k,v)-> { if (w.isWritableProperty(k)) w.setPropertyValue(k,v); });
        return repo.save(db);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException("No existe estufa " + id);
        repo.deleteById(id);
    }
}
