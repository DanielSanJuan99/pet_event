package com.events.petevents;

import com.events.petevents.model.Evento;
import com.events.petevents.repository.EventoRepository;
import com.events.petevents.service.EventoService;
import com.events.petevents.exception.EventoNotFound;

import org.springframework.data.domain.Sort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventoServiceTest {
    
    private EventoRepository eventoRepository;
    private EventoService eventoService;

    @BeforeEach
    public void setUp() {
        eventoRepository = mock(EventoRepository.class);
        eventoService = new EventoService(eventoRepository);
    }

    @Test
    public void testGetAllEvents() {
        Evento evento1 = new Evento(1L, "Feria de Adopción Canina", "Adopción", "Evento para encontrar hogar a perros rescatados", LocalDate.parse("2025-05-15"), "Parque Central", 300, "Fundación Patitas Felices");

        Evento evento2 = new Evento(2L, "Charla sobre Tenencia Responsable", "Educación", "Conferencia sobre cuidados y bienestar animal", LocalDate.parse("2025-06-10"), "Centro Cultural", 150, "Veterinaria AnimalCare");

        List<Evento> eventos = Arrays.asList(evento1, evento2);

        when(eventoRepository.findAll(Sort.by("id"))).thenReturn(eventos);

        assertEquals(2, eventos.size());
        assertEquals("Feria de Adopción Canina", eventos.get(0).getNombre());
        assertEquals("Educación", eventos.get(1).getTipo_evento());
    }

    @Test
    public void testGetEventoById() {
        Evento evento = new Evento(1L, "Feria de Adopción Canina", "Adopción", "Evento para encontrar hogar a perros rescatados", LocalDate.parse("2025-05-15"), "Parque Central", 300, "Fundación Patitas Felices");

        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));

        Evento foundEvento = eventoService.getEventoById(1L);
        assertEquals("Feria de Adopción Canina", foundEvento.getNombre());
        assertEquals(300, foundEvento.getParticipantes());
    }

    @Test
    public void testGetEventoByIdNotFound() {
        when(eventoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EventoNotFound.class, () -> eventoService.getEventoById(99L));
    }

}
