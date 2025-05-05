package com.events.petevents;

import com.events.petevents.model.Evento;
import com.events.petevents.repository.EventoRepository;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@DataJpaTest
public class EventoRepositoryTest {

    @Autowired
    private EventoRepository eventoRepository;

    @Test
    public void testFindAndSave () {
        Evento evento = new Evento(1L, "Feria de Adopción Canina", "Adopción", "Evento para encontrar hogar a perros rescatados", LocalDate.parse("2025-05-15"), "Parque Central", 300, "Fundación Patitas Felices");

        eventoRepository.save(evento);

        Optional<Evento> foundEvento = eventoRepository.findById(1L);
        assertTrue(foundEvento.isPresent());
        assertEquals("Feria de Adopción Canina", foundEvento.get().getNombre());
    }
    
}
