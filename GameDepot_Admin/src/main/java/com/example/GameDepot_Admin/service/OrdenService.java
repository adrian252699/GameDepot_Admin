package com.example.GameDepot_Admin.service;

import com.example.GameDepot_Admin.dto.admin.OrdenDTO;
import com.example.GameDepot_Admin.dto.admin.UsuarioDTO;
import com.example.GameDepot_Admin.dto.enums.EstadoOrden;
import com.example.GameDepot_Admin.dto.usuario.MostrarUsuarioDTO;
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

    public  List<OrdenDTO> getOrdenes(){
        List<Orden> ordenes = ordenRepository.findAll();
        return OrdenMapper.toOrdenDTOList(ordenes);
    }

    public List<OrdenDTO> getOrdenesEntregadasDelMes() {
        LocalDate primerDiaDelMes = LocalDate.now().withDayOfMonth(1);

        List<Orden> ordenesMes = ordenRepository
                .findByFechaGreaterThanEqualAndFechaLessThanOrderByFechaDesc(
                        primerDiaDelMes.atStartOfDay(),
                        primerDiaDelMes.plusMonths(1).atStartOfDay()
                )
                .stream()
                .filter(orden -> orden.getEstado() == EstadoOrden.ENTREGADO)
                .toList();

        return OrdenMapper.toOrdenDTOList(ordenesMes);
    }

    public List<MostrarUsuarioDTO> getUsuariosPorOrdenEntregada() {

        List<OrdenDTO> ordenesMes = this.getOrdenesEntregadasDelMes().stream()
                .filter(orden -> EstadoOrden.ENTREGADO.name().equals(orden.getEstado()))
                .toList();


        List<Usuario> usuariosPorOrden = new ArrayList<>();
        for (OrdenDTO orden : ordenesMes) {
            usuariosPorOrden.add(ordenRepository.findUsuarioByOrdenId(orden.getId()).orElse(null));
        }

        return UsuarioMapper.toMostrarUsuarioDTOList(usuariosPorOrden);
    }

    public List<MostrarUsuarioDTO> getUsuariosPorOrden() {

        List<Orden> ordenes = ordenRepository.findAll();

        List<OrdenDTO>ordenesDTO = OrdenMapper.toOrdenDTOList(ordenes);

        List<Usuario> usuariosPorOrden = new ArrayList<>();

        for (OrdenDTO ordenDTO : ordenesDTO) {
            usuariosPorOrden.add(ordenRepository.findUsuarioByOrdenId(ordenDTO.getId()).orElse(null));
        }

        return UsuarioMapper.toMostrarUsuarioDTOList(usuariosPorOrden);
    }
}
