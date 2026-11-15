package com.example.GameDepot_Admin.controllers.restControllers;

import com.example.GameDepot_Admin.dto.orden.ActualizarEstadosOrdenDTO;
import com.example.GameDepot_Admin.dto.videojuego.ClaveDTO;
import com.example.GameDepot_Admin.service.ClaveService;
import com.example.GameDepot_Admin.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orden")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PatchMapping("/{id}")
    public ActualizarEstadosOrdenDTO actualizarEstadosOrden(@PathVariable Long id, @RequestBody ActualizarEstadosOrdenDTO actualizarEstadosOrdenDTO) {
        return ordenService.actualizarEstadosOrden(id, actualizarEstadosOrdenDTO);
    }

}
