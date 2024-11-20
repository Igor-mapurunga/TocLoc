package com.Tocloc.Tocloc.controller;

import com.Tocloc.Tocloc.entities.Local;
import com.Tocloc.Tocloc.entities.Reserva;
import com.Tocloc.Tocloc.entities.User.User;
import com.Tocloc.Tocloc.entities.User.UserRoles;
import com.Tocloc.Tocloc.service.LocalService;
import com.Tocloc.Tocloc.service.ReservaService;
import com.Tocloc.Tocloc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locais")
public class LocalController {
    @Autowired
    private LocalService localService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservaService reservaService;
    @GetMapping
    public ResponseEntity<List<Local>> getAllLocais() {
        List<Local> locais = localService.findAll();
        return new ResponseEntity<>(locais, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Local> getLocalById(@PathVariable Long id) {
        Local local = localService.findById(id);
        return new ResponseEntity<>(local, HttpStatus.OK);
    }
    @PostMapping("/{proprietarioId}")
    public ResponseEntity<Local> createLocal(@PathVariable Long proprietarioId, @RequestBody Local local) {
        User proprietario = userService.findById(proprietarioId);

        local.setProprietario(proprietario);

        Local novoLocal = localService.save(local);
        return new ResponseEntity<>(novoLocal, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Local> updateLocal(@PathVariable Long id, @RequestBody Local localDetails) {
        Local updatedLocal = localService.update(id, localDetails);
        return new ResponseEntity<>(updatedLocal, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocalById(@PathVariable Long id) {
        localService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/{localId}/locar/{usuarioId}")
    public ResponseEntity<Reserva> realizarLocacao(@PathVariable Long localId, @PathVariable Long usuarioId, @RequestBody Reserva reservaRequest) {
        Local local = localService.findById(localId);
        User usuario = userService.findById(usuarioId);
        if (!UserRoles.USUARIO.equals(usuario.getTypeOfUser())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        local.setUsuarioLocador(usuario);
        localService.save(local);
        reservaRequest.setLocal(local);
        reservaRequest.setUsuario(usuario);
        try {
            Reserva reservaCriada = reservaService.save(reservaRequest);
            return new ResponseEntity<>(reservaCriada, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{localId}/reservas")
    public ResponseEntity<List<Reserva>> obterReservas(@PathVariable Long localId){
        List<Reserva> reservas = reservaService.findReservationByLocal(localId);
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @GetMapping("/{localId}/reservas/{reservaId}")
    public ResponseEntity<Reserva> obterDetalhesReserva(@PathVariable Long localId, @PathVariable Long reservaId) {
        Reserva reserva = reservaService.findById(reservaId);
        if (!reserva.getLocal().getId().equals(localId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }
    @DeleteMapping("/{localId}/reservas/{reservaId}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long localId, @PathVariable Long reservaId) {
        Reserva reserva = reservaService.findById(reservaId);

        if (!reserva.getLocal().getId().equals(localId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        reservaService.deleteById(reservaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
