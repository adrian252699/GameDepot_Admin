package com.example.GameDepot_Admin.dto.videojuego;

import com.example.GameDepot_Admin.model.videojuego.Videojuego;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyDTO {
    private Long id;
    private String codigo;
    private Videojuego videojuego;
}
