package com.example.GameDepot_Admin.model.videojuego;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Videojuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 155)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(length = 250)
    private String descripcion;

    @Column(nullable = false)
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "plataforma_id", nullable = false)
    private Plataforma plataforma;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @OneToMany(mappedBy = "videojuego")
    private List<Keys> claves = new ArrayList<>();

    @Column()
    @OneToMany(mappedBy ="videojuego",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImagenProducto> imagenes;

    public Videojuego(String nombre, Categoria categoria, String descripcion, BigDecimal precio, Integer stock, Boolean activo, Plataforma plataforma, Region region) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.activo = activo;
        this.plataforma = plataforma;
        this.region = region;
    }
}
