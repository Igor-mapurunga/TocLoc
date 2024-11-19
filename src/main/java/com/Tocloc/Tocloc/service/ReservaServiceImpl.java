package com.Tocloc.Tocloc.service;
import com.Tocloc.Tocloc.dao.ReservaRepository;
import com.Tocloc.Tocloc.entities.Reserva;
import com.Tocloc.Tocloc.entities.Local;
import com.Tocloc.Tocloc.entities.User.User;
import com.Tocloc.Tocloc.exceptions.ReservaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private LocalService localService;
    @Autowired
    private UserService userService;
    @Override
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }
    @Override
    public Reserva findById(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException("Reserva não encontrada com ID: " + id));
    }
    @Override
    public Reserva save(Reserva reserva) {
        Local local = localService.findById(reserva.getLocal().getId());
        reserva.setLocal(local);
        User usuario = userService.findById(reserva.getUsuario().getId());
        reserva.setUsuario(usuario);
        List<Reserva> reservasExistentes = findAll().stream().filter(r -> r.getLocal().getId().equals(local.getId())).toList();
        for (Reserva r : reservasExistentes) {
            if ((reserva.getDataHoraInicio().isBefore(r.getDataHoraFim()) && reserva.getDataHoraFim().isAfter(r.getDataHoraInicio())) ||
                    (reserva.getDataHoraInicio().equals(r.getDataHoraInicio()) || reserva.getDataHoraFim().equals(r.getDataHoraFim()))) {
                throw new IllegalArgumentException("Conflito de horário: o local já está reservado nesse período.");
            }
        }
        return reservaRepository.save(reserva);
    }
    @Override
    public void deleteById(Long id) {
        Reserva reserva = findById(id);
        reservaRepository.deleteById(reserva.getId());
    }
}
