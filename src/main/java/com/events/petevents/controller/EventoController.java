package com.events.petevents.controller;

import com.events.petevents.exception.EventoNotFound;
import com.events.petevents.model.Evento;
import com.events.petevents.service.EventoService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/eventos")
public class EventoController {
    
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<Evento> getAllEvents() {
        return eventoService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Evento getEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id)
                .orElseThrow(() -> new EventoNotFound(id));
    }
}
