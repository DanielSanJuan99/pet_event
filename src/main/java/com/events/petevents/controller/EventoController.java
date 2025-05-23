package com.events.petevents.controller;

import com.events.petevents.model.Evento;
import com.events.petevents.model.ResponseWrapper;
import com.events.petevents.service.EventoService;
import com.events.petevents.hateoas.EventoModelAssembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/eventos")
public class EventoController {
    
    private final EventoService eventoService;
    private final EventoModelAssembler assembler;

    public EventoController(EventoService eventoService, EventoModelAssembler assembler) {
        this.eventoService = eventoService;
        this.assembler = assembler;
    }

    // Recuperar todos los Eventos
    @GetMapping
    public ResponseEntity<?> getAllEvents() {
        log.info("GET /eventos - Recuperando todos los eventos");

        List<Evento> eventos = eventoService.getAllEvents();

        if (eventos.isEmpty()) {
            log.warn("No hay eventos registrados");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay eventos registrados.");
        }

        List<EntityModel<Evento>> modelos = eventos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
            CollectionModel.of(modelos,
                linkTo(methodOn(EventoController.class).getAllEvents()).withSelfRel()));
    }

    // Recuperar Evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Evento>> getEventoById(@PathVariable Long id) {
        log.info("GET /eventos/{} - Recuperando evento por ID", id);

        Evento evento = eventoService.getEventoById(id);

        return ResponseEntity.ok(
                assembler.toModel(evento));
    }

    // Crear Evento nuevo
    @PostMapping
    public ResponseEntity<EntityModel<Evento>> createEvento(@Valid @RequestBody Evento evento) {
        log.info("POST /eventos - Creando nuevo evento: {}", evento.getNombre());
        Evento createdEvento = eventoService.createEvento(evento);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(createdEvento));
    }

    // Actualizar Evento por ID
    @PostMapping("/{id}")
    public ResponseEntity<EntityModel<Evento>> updateEvento(@PathVariable Long id, @RequestBody Evento evento) {
        log.info("POST /eventos/{} - Actualizando evento: {}", id);

        Evento updatedEvento = eventoService.updateEvento(id, evento);

        return ResponseEntity.ok(assembler
                .toModel(updatedEvento));
    }

    // Eliminar Evento por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteEvento(@PathVariable Long id) {
        log.warn("DELETE /eventos/{} - Eliminando evento", id);
        eventoService.deleteEvento(id);
        return ResponseEntity.ok(
            new ResponseWrapper<>(
                "Evento eliminado",
                1,
                null
            ));
    }
}
