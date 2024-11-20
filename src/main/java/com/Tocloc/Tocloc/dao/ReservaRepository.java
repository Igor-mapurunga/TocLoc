package com.Tocloc.Tocloc.dao;

import com.Tocloc.Tocloc.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuarioId(Long usuarioId);
    List<Reserva> findByLocalId(Long localId);
}
