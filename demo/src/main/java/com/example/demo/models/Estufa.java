package com.example.demo.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "estufas")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Estufa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String marca;
    private String modelo;
    private String tipo;        // gas | electrica | mixta
    private Integer potencia;   // watts
    @Column(length = 500) private String descripcion;

    // N:1 (muchas estufas pertenecen a una cocina)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cocina_id")
    @JsonIgnoreProperties({"estufas","hibernateLazyInitializer","handler"})
    private Cocina cocina;

    // ===== getters/setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Integer getPotencia() { return potencia; }
    public void setPotencia(Integer potencia) { this.potencia = potencia; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Cocina getCocina() { return cocina; }
    public void setCocina(Cocina cocina) { this.cocina = cocina; }
}

