package com.Tocloc.Tocloc.service;

import com.Tocloc.Tocloc.entities.Reserva;

import java.util.List;

public interface ReservaService {

    List<Reserva> findAll();
    List<Reserva> findReservationByUser(Long id);
    List<Reserva> findReservationByLocal(Long id);
    Reserva findById(Long id);
    Reserva save(Reserva reserva);
    void deleteById(Long id);
    void realizarCheckin(Long reservaId);
}
