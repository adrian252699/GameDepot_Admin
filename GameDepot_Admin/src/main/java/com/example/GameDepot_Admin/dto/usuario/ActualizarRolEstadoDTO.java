package com.example.GameDepot_Admin.dto.usuario;

import com.example.GameDepot_Admin.dto.enums.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActualizarRolEstadoDTO {
    private RolUsuario rol;
    private Boolean activo;
}
