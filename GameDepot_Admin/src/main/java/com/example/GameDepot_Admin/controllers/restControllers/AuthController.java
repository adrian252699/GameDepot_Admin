package com.example.GameDepot_Admin.controllers.restControllers;

import com.example.GameDepot_Admin.dto.usuario.InicioSesionRequestDTO;
import com.example.GameDepot_Admin.dto.usuario.SesionResponseDTO;
import com.example.GameDepot_Admin.service.jwt.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping
    public ResponseEntity<SesionResponseDTO> iniciarSesion(@Valid @RequestBody InicioSesionRequestDTO request) {
        SesionResponseDTO respuesta = service.iniciarSesion(request);
        return ResponseEntity.ok(respuesta);
    }
}
