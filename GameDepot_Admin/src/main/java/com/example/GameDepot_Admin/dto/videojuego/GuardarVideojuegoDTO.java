package com.example.GameDepot_Admin.dto.videojuego;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuardarVideojuegoDTO {
    private String nombre;
    private CategoriaDTO categoria;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private Boolean activo;
    private PlataformaDTO plataforma;
    private RegionDTO region;
}
