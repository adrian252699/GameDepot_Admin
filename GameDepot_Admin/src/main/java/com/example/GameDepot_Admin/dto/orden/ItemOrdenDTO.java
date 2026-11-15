package com.example.GameDepot_Admin.dto.orden;

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
public class ItemOrdenDTO {
    private String nombreVideojuego;
    private BigDecimal precioUnitario;
    private Integer cantidad;
    private List<String> claves;

}
