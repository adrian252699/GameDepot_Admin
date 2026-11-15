package com.example.GameDepot_Admin.dto.videojuego;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClaveDTO {
    private Long id;
    private String codigo;
    private Long itemId;
}
