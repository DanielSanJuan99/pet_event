package com.events.petevents.service;

import com.events.petevents.model.Evento;
import com.events.petevents.exception.EventoNotFound;
import com.events.petevents.repository.EventoRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventoService {

    @Autowired
    private EventoRepository repo;

    // Recuperar todos los Eventos
    public List<Evento> getAllEvents() {
        return repo.findAll(Sort.by("id")
            .ascending());
    }

    // Recuperar Evento por ID
    public Evento getEventoById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EventoNotFound(id));
    }

    // Crear Evento nuevo
    public Evento createEvento(Evento evento) {

        if (repo.existsById(evento.getId())) {
            log.error("Evento con ID ya existe: " + evento.getId());
            throw new EventoNotFound(evento.getId());
        }

        return repo.save(evento);
    }

    // Actualizar Evento por ID
    public Evento updateEvento(Long id, Evento eventoUpdt) {

        Evento eventoExiste = repo.findById(id)
                .orElseThrow(() -> new EventoNotFound(id));

        eventoExiste.setNombre(eventoUpdt.getNombre());
        eventoExiste.setTipo_evento(eventoUpdt.getTipo_evento());
        eventoExiste.setDescripcion(eventoUpdt.getDescripcion());
        eventoExiste.setFecha(eventoUpdt.getFecha());
        eventoExiste.setLugar(eventoUpdt.getLugar());
        eventoExiste.setParticipantes(eventoUpdt.getParticipantes());
        eventoExiste.setOrganizador(eventoUpdt.getOrganizador());

        return repo.save(eventoUpdt);
    }

    // Eliminar Evento por ID
    public void deleteEvento(Long id) {
        Evento evento = repo.findById(id)
                .orElseThrow(() -> new EventoNotFound(id));
        repo.delete(evento);
    }

}
