
package com.example.demo.controllers;

import com.example.demo.models.Estufa;
import com.example.demo.services.EstufaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/estufas")

public class EstufaController {

    private final EstufaService service;

    public EstufaController(EstufaService service) { this.service = service; }

    @GetMapping public ResponseEntity<List<Estufa>> getAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Estufa> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // listar estufas por cocina
    @GetMapping("/cocina/{cocinaId}")
    public ResponseEntity<List<Estufa>> getByCocina(@PathVariable Long cocinaId) {
        return ResponseEntity.ok(service.findByCocina(cocinaId));
    }

    @PostMapping
    public ResponseEntity<Estufa> create(@RequestBody Estufa estufa) {
        Estufa saved = service.create(estufa);
        return ResponseEntity.created(URI.create("/api/estufas/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estufa> update(@PathVariable Long id, @RequestBody Estufa estufa) {
        return ResponseEntity.ok(service.update(id, estufa));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Estufa> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(service.patch(id, fields));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
