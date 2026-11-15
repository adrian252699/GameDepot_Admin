package com.example.GameDepot_Admin.service;

import com.example.GameDepot_Admin.dto.videojuego.ClaveDTO;
import com.example.GameDepot_Admin.exception.NegocioException;
import com.example.GameDepot_Admin.exception.RecursoNoEncontradoException;
import com.example.GameDepot_Admin.model.orden.ItemOrden;
import com.example.GameDepot_Admin.model.videojuego.Clave;
import com.example.GameDepot_Admin.repository.ClaveRepository;
import com.example.GameDepot_Admin.repository.ItemOrdenRepository;
import com.example.GameDepot_Admin.service.mappers.VideojuegoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClaveService {

    @Autowired
    private ClaveRepository claveRepository;

    @Autowired
    private ItemOrdenRepository itemOrdenRepository;

    public List<String> obtenerClaveByOrdenId(Long itemId){
        List<String> codigos = claveRepository.findByItemOrden_Id(itemId)
                .stream()
                .map(Clave::getCodigo)
                .toList();

        System.out.println("ClavesDTO: " + codigos);

        return codigos;
    }

    @Transactional
    public void asignarClave(Long itemId, Long claveId) {
        ItemOrden item = itemOrdenRepository.buscarParaAsignar(itemId)
                .orElseThrow(
                        () -> new RecursoNoEncontradoException("Item no encontrado")
                );

        Clave clave = claveRepository.buscarParaAsignar(claveId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Clave no encontrada")
                );

        if (clave.getItemOrden() != null) {
            if (itemId.equals(clave.getItemOrden().getId())) {
                return; // La clave ya está asignada a este ítem.
            }

            throw new NegocioException("La clave ya pertenece a otro ítem");
        }

        if (claveRepository.countByItemOrden_Id(itemId) >= item.getCantidad()) {
            throw new NegocioException("El ítem ya tiene todas sus claves");
        }

        clave.setItemOrden(item);
    }
}
