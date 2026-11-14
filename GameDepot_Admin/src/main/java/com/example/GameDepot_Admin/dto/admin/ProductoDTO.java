package com.example.GameDepot_Admin.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String key;
    private String plataforma;
    private String region;
    private Double precio;
    private Integer stock;
    private String categoria;
    private String descripcion;
}
