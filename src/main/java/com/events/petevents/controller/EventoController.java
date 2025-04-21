package com.events.petevents.controller;

// import com.events.petevents.exception.EventoNotFound;
import com.events.petevents.model.Evento;
import com.events.petevents.model.ResponseWrapper;
import com.events.petevents.service.EventoService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/eventos")
public class EventoController {
    
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents() {
        List<Evento> eventos = eventoService.getAllEvents();

        if (eventos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay eventos registrados.");
        }

        return ResponseEntity.ok(
            new ResponseWrapper<>(
                "OK",
                eventos.size(),
                eventos
            ));
    }

    @GetMapping("/{id}")
    public Evento getEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id);
    }
}
