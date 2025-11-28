package com.example.demo.controllers;

import com.example.demo.models.Cocina;
import com.example.demo.services.CocinaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cocinas")
public class CocinaController {

    private final CocinaService service;

    public CocinaController(CocinaService service) { this.service = service; }

    @GetMapping public ResponseEntity<List<Cocina>> getAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Cocina> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cocina> create(@RequestBody Cocina cocina) {
        Cocina saved = service.create(cocina);
        return ResponseEntity.created(URI.create("/api/cocinas/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cocina> update(@PathVariable Long id, @RequestBody Cocina cocina) {
        return ResponseEntity.ok(service.update(id, cocina));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
