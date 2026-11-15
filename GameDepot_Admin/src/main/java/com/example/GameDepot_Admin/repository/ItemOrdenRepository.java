package com.example.GameDepot_Admin.repository;

import com.example.GameDepot_Admin.model.orden.ItemOrden;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemOrdenRepository extends JpaRepository<ItemOrden, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select i from ItemOrden i where i.id = :id")
    Optional<ItemOrden> buscarParaAsignar(@Param("id") Long id);

    @Query("""
    select distinct i
    from ItemOrden i
    join fetch i.videojuego
    left join fetch i.claves
    where i.orden.id = :ordenId
    order by i.id
    """)
    List<ItemOrden> buscarItemsDelPedido(@Param("ordenId") Long ordenId);

}
