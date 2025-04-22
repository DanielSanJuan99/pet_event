package com.events.petevents.controller;

// import com.events.petevents.exception.EventoNotFound;
import com.events.petevents.model.Evento;
import com.events.petevents.model.ResponseWrapper;
import com.events.petevents.service.EventoService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/eventos")
public class EventoController {
    
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // Recuperar todos los Eventos
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

    // Recuperar Evento por ID
    @GetMapping("/{id}")
    public Evento getEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id);
    }

    // Crear Evento nuevo
    @PostMapping
    public ResponseEntity<ResponseWrapper<Evento>> createEvento(@RequestBody Evento evento) {
        Evento createdEvento = eventoService.createEvento(evento);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                    "Evento creado",
                    1,
                    List.of(createdEvento)
                ));
    }

    // Actualizar Evento por ID
    @PostMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Evento>> updateEvento(@PathVariable Long id, @RequestBody Evento evento) {
        Evento updatedEvento = eventoService.updateEvento(id, evento);
        return ResponseEntity.ok(
            new ResponseWrapper<>(
                "Evento actualizado",
                1,
                List.of(updatedEvento)
            ));
    }

    // Eliminar Evento por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.ok(
            new ResponseWrapper<>(
                "Evento eliminado",
                1,
                null
            ));
    }
}
