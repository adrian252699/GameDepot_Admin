package com.example.GameDepot_Admin.service.mappers;

import com.example.GameDepot_Admin.dto.admin.UsuarioDTO;
import com.example.GameDepot_Admin.dto.enums.RolUsuario;
import com.example.GameDepot_Admin.dto.usuario.ActualizarRolEstadoDTO;
import com.example.GameDepot_Admin.dto.usuario.MostrarUsuarioDTO;
import com.example.GameDepot_Admin.model.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static MostrarUsuarioDTO toMostrarUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new MostrarUsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getFechaRegistro(),
                usuario.getRol().toString(),
                usuario.getPais(),
                usuario.getActivo()
        );
    }

    public static ActualizarRolEstadoDTO toActualizarRolEstadoDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new ActualizarRolEstadoDTO(
                usuario.getRol(),
                usuario.getActivo()
        );
    }

    public static Usuario toUsuarioEntity(MostrarUsuarioDTO mostrarUsuarioDTO) {
        if (mostrarUsuarioDTO == null) {
            return null;
        }

        return new Usuario(
                mostrarUsuarioDTO.getId(),
                mostrarUsuarioDTO.getNombre(),
                mostrarUsuarioDTO.getEmail(),
                RolUsuario.valueOf(mostrarUsuarioDTO.getRol()),
                mostrarUsuarioDTO.getFechaRegistro(),
                mostrarUsuarioDTO.getPais()
        );
    }

    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getFechaRegistro(),
                usuario.getRol().toString(),
                usuario.getPais(),
                usuario.getActivo()
        );
    }

    public static List<MostrarUsuarioDTO> toMostrarUsuarioDTOList(List<Usuario> usuarios) {
        List<MostrarUsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            MostrarUsuarioDTO usuarioDTO = UsuarioMapper.toMostrarUsuarioDTO(usuario);
            usuarioDTOS.add(usuarioDTO);
        }
        return usuarioDTOS;
    }

}
