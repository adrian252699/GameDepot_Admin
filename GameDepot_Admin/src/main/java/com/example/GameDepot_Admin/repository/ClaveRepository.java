package com.example.GameDepot_Admin.repository;

import com.example.GameDepot_Admin.model.videojuego.Clave;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClaveRepository extends JpaRepository<Clave, Long> {
    List<Clave> findByItemOrden_Id(Long itemId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Clave c where c.id = :id")
    Optional<Clave> buscarParaAsignar(@Param("id") Long id);

    long countByItemOrden_Id(Long itemId);
}
