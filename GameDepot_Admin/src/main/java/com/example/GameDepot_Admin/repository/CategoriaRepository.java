package com.example.GameDepot_Admin.repository;

import com.example.GameDepot_Admin.model.videojuego.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
