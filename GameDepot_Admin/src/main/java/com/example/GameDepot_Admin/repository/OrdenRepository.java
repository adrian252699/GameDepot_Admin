package com.example.GameDepot_Admin.repository;

import com.example.GameDepot_Admin.model.orden.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
