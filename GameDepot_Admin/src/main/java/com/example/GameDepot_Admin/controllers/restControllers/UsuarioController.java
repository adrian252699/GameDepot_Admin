package com.example.GameDepot_Admin.controllers.restControllers;

import com.example.GameDepot_Admin.dto.admin.UsuarioDTO;
import com.example.GameDepot_Admin.dto.usuario.ActualizarRolEstadoDTO;
import com.example.GameDepot_Admin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PatchMapping("/{id}")
    public ActualizarRolEstadoDTO actualizarRolEstado(@PathVariable Long id, @RequestBody ActualizarRolEstadoDTO datosActualizados) {
        return usuarioService.actualizarRolEstado(id,datosActualizados);
    }
}
