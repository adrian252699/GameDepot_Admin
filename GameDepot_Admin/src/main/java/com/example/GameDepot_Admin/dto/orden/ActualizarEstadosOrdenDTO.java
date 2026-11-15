package com.example.GameDepot_Admin.dto.orden;

import com.example.GameDepot_Admin.dto.enums.EstadoOrden;
import com.example.GameDepot_Admin.dto.enums.EstadoPago;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActualizarEstadosOrdenDTO {
    private EstadoOrden estado;
    private EstadoPago estadoPago;
}
