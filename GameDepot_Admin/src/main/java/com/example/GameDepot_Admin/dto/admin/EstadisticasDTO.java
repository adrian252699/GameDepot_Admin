package com.example.GameDepot_Admin.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadisticasDTO {
    private Double ingresos;
    private Integer ventas;
    private Integer usuarios;
    private Integer licencias;
}
