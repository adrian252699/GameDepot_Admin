package com.example.GameDepot_Admin.service;

import com.example.GameDepot_Admin.dto.admin.EstadisticasDTO;
import com.example.GameDepot_Admin.dto.admin.OrdenDTO;
import com.example.GameDepot_Admin.dto.enums.EstadoOrden;
import com.example.GameDepot_Admin.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadisticasService {
    @Autowired
    private OrdenService ordenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VideojuegoRepository  videojuegoRepository;

    public EstadisticasDTO getEstadisticasDTO() {
        List<OrdenDTO> ordenes = ordenService.getOrdenesMes().stream()
                .filter(orden -> EstadoOrden.ENTREGADO.name().equals(orden.getEstado()))
                .toList();

        double ingresosMensuales = 0.0;

        for (OrdenDTO orden : ordenes) {
            ingresosMensuales += orden.getTotal();
        }
        Integer ventas = ordenes.size();

        Integer cantidadUsuarios = usuarioService.getUsuariosMensuales().size();

        Integer cantidadVideojuegos = videojuegoRepository.findAll().size();

        EstadisticasDTO estadisticasDTO =  new EstadisticasDTO();

        estadisticasDTO.setIngresos(ingresosMensuales);
        estadisticasDTO.setVentas(ventas);
        estadisticasDTO.setUsuarios(cantidadUsuarios);
        estadisticasDTO.setLicencias(cantidadVideojuegos);

        return estadisticasDTO;
    }


}
