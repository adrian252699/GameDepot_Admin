package com.example.GameDepot_Admin.service;

import com.example.GameDepot_Admin.dto.usuario.MostrarUsuarioDTO;
import com.example.GameDepot_Admin.exception.RecursoNoEncontradoException;
import com.example.GameDepot_Admin.model.usuario.Usuario;
import com.example.GameDepot_Admin.repository.UsuarioRepository;
import com.example.GameDepot_Admin.service.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<MostrarUsuarioDTO> mostrarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return UsuarioMapper.toMostrarUsuarioDTOList(usuarios);
    }

    public MostrarUsuarioDTO obtenerPorId(Long id) {
        return UsuarioMapper.toMostrarUsuarioDTO(usuarioRepository.findById(id).orElseThrow(
                    () -> new RecursoNoEncontradoException("Usuario con ID " + id + " no encontrado.")
                )
        );
    }

    public List<Usuario> getUsuariosMensuales() {
        LocalDate primerDiaDelMes = LocalDate.now().withDayOfMonth(1);

        return usuarioRepository.findByFechaRegistroGreaterThanEqualAndFechaRegistroLessThanOrderByFechaRegistroDesc(
                primerDiaDelMes.atStartOfDay(),
                primerDiaDelMes.plusMonths(1).atStartOfDay()
        );
    }

    public MostrarUsuarioDTO obtenerPorEmail(String email) {
        return UsuarioMapper.toMostrarUsuarioDTO(usuarioRepository.findByEmail(email).orElse(null));
    }
}
