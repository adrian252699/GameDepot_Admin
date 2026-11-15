package com.example.GameDepot_Admin.repository;

import com.example.GameDepot_Admin.model.orden.Orden;
import com.example.GameDepot_Admin.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    List<Orden> findByFechaGreaterThanEqualAndFechaLessThanOrderByFechaDesc(
            LocalDateTime inicioMes,
            LocalDateTime inicioMesSiguiente
    );

    @Query("""
        select u
        from Orden o
        join o.usuario u
        where o.id = :ordenId
        """)
    Optional<Usuario> findUsuarioByOrdenId(@Param("ordenId") Long ordenId);
}
