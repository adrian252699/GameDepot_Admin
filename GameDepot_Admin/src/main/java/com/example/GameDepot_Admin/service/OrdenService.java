package com.example.GameDepot_Admin.service;

import com.example.GameDepot_Admin.dto.admin.OrdenDTO;
import com.example.GameDepot_Admin.dto.admin.UsuarioDTO;
import com.example.GameDepot_Admin.model.orden.Orden;
import com.example.GameDepot_Admin.model.usuario.Usuario;
import com.example.GameDepot_Admin.repository.OrdenRepository;
import com.example.GameDepot_Admin.service.mappers.OrdenMapper;
import com.example.GameDepot_Admin.service.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    public List<OrdenDTO> getOrdenesMes() {
        LocalDate primerDiaDelMes = LocalDate.now().withDayOfMonth(1);

        List<Orden> ordenesMes = ordenRepository.findByFechaGreaterThanEqualAndFechaLessThanOrderByFechaDesc(
                primerDiaDelMes.atStartOfDay(),
                primerDiaDelMes.plusMonths(1).atStartOfDay()
        );

        return OrdenMapper.toOrdenDTOList(ordenesMes);
    }

    public List<UsuarioDTO> getUsuariosPorOrden() {
        LocalDate primerDiaDelMes = LocalDate.now().withDayOfMonth(1);

        List<Orden> ordenesMes = ordenRepository.findByFechaGreaterThanEqualAndFechaLessThanOrderByFechaDesc(
                primerDiaDelMes.atStartOfDay(),
                primerDiaDelMes.plusMonths(1).atStartOfDay()
        );

        List<Usuario> usuariosPorOrden = new ArrayList<>();
        for (Orden orden : ordenesMes) {
            usuariosPorOrden.add(ordenRepository.findUsuarioByOrdenId(orden.getId()).orElse(null));
        }

        return UsuarioMapper.toUsuarioDTOList(usuariosPorOrden);
    }
}
