package com.Tocloc.Tocloc.controller;

import com.Tocloc.Tocloc.entities.Local;
import com.Tocloc.Tocloc.entities.User;
import com.Tocloc.Tocloc.service.LocalService;
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
}
