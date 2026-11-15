package com.example.GameDepot_Admin.model.videojuego;

import com.example.GameDepot_Admin.model.orden.ItemOrden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "claves_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Clave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemOrden itemOrden;

}
