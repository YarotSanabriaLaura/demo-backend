package com.example.demo.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cocinas")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Cocina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String ubicacion;

    // 1:N  (una cocina tiene muchas estufas)
    @OneToMany(mappedBy = "cocina", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"cocina","hibernateLazyInitializer","handler"})
    private List<Estufa> estufas = new ArrayList<>();

    // ===== getters/setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public List<Estufa> getEstufas() { return estufas; }
    public void setEstufas(List<Estufa> estufas) { this.estufas = estufas; }
}

