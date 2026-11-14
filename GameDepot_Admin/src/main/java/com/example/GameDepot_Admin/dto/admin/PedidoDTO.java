package com.example.GameDepot_Admin.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO {
    private Long id;
    private String estado;
    private LocalDateTime fecha;
    private String folio;
    private Double total;
    private Long usuarioId;
    private String estadoPago;
}
