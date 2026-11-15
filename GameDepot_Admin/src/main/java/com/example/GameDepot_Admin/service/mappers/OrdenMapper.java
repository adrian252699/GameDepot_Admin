package com.example.GameDepot_Admin.service.mappers;

import com.example.GameDepot_Admin.dto.admin.OrdenDTO;
import com.example.GameDepot_Admin.dto.enums.EstadoOrden;
import com.example.GameDepot_Admin.dto.orden.ActualizarEstadosOrdenDTO;
import com.example.GameDepot_Admin.model.orden.Orden;

import java.util.ArrayList;
import java.util.List;

public class OrdenMapper {
    public static OrdenDTO toOrdenDTO(Orden orden) {

        OrdenDTO ordenDTO = new OrdenDTO();

        ordenDTO.setId(orden.getId());
        ordenDTO.setEstado(orden.getEstado() == null ? null : orden.getEstado().name());
        ordenDTO.setFecha(orden.getFecha());
        ordenDTO.setFolio(orden.getFolio());
        ordenDTO.setTotal(orden.getTotal().doubleValue());
        ordenDTO.setUsuarioId(orden.getUsuario().getId());
        ordenDTO.setEstadoPago(orden.getEstadoPago() == null ? null : orden.getEstadoPago().name());

        return ordenDTO;
    }

    public static List<OrdenDTO> toOrdenDTOList(List<Orden> ordenes) {

        List<OrdenDTO> ordenesDTO = new ArrayList<>();
        for (Orden orden : ordenes) {
            OrdenDTO ordenDTO = OrdenMapper.toOrdenDTO(orden);
            ordenesDTO.add(ordenDTO);
        }
        return ordenesDTO;
    }

    public static ActualizarEstadosOrdenDTO toActualizarEstadosOrdenDTO(Orden orden) {
        ActualizarEstadosOrdenDTO ordenDTO = new ActualizarEstadosOrdenDTO();

        ordenDTO.setEstado(orden.getEstado());
        ordenDTO.setEstadoPago(orden.getEstadoPago());

        return ordenDTO;
    }
}
