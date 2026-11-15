package com.example.GameDepot_Admin.service.mappers;

import com.example.GameDepot_Admin.dto.admin.OrdenDTO;
import com.example.GameDepot_Admin.dto.admin.UsuarioDTO;
import com.example.GameDepot_Admin.dto.enums.RolUsuario;
import com.example.GameDepot_Admin.dto.usuario.MostrarUsuarioDTO;
import com.example.GameDepot_Admin.model.orden.Orden;
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
                usuario.getRol().toString(),
                usuario.getFechaRegistro(),
                usuario.getPais()
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

    public static List<UsuarioDTO> toUsuarioDTOList(List<Usuario> usuarios) {
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioDTO usuarioDTO = UsuarioMapper.toUsuarioDTO(usuario);
            usuarioDTOS.add(usuarioDTO);
        }
        return usuarioDTOS;
    }
}
