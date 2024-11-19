package com.Tocloc.Tocloc.service;

import com.Tocloc.Tocloc.entities.Reserva;

import java.util.List;

public interface ReservaService {

    List<Reserva> findAll();
    Reserva findById(Long id);
    Reserva save(Reserva reserva);
    void deleteById(Long id);
}
