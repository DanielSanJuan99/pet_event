package com.events.petevents;

import com.events.petevents.controller.EventoController;
import com.events.petevents.hateoas.EventoModelAssembler;
import com.events.petevents.model.Evento;
import com.events.petevents.service.EventoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@WebMvcTest(EventoController.class)
public class EventoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private EventoService eventoService;

    @SuppressWarnings("removal")
    @MockBean
    private EventoModelAssembler eventoModelAssembler;

    @Test
    @WithMockUser(username = "admin", password = "admin123", roles = {"ADMIN"})
    public void testGetEventoById() throws Exception {

        Evento evento = new Evento(9L, "Maratón Canina", "Deporte", "Carrera de resistencia para perros y dueños", LocalDate.parse("2026-01-25"), "Parque Metropolitano", 400, "Club Deportivo Canino");

        EntityModel<Evento> eventoModel = EntityModel.of(evento,
            linkTo(methodOn(EventoController.class)
                    .getEventoById(evento.getId()))
                    .withSelfRel(),
            
            linkTo(methodOn(EventoController.class)
                    .getAllEvents())
                    .withRel("all"),

            linkTo(methodOn(EventoController.class)
                    .updateEvento(evento.getId(), evento))
                    .withRel("update"),
            
            linkTo(methodOn(EventoController.class)
                    .deleteEvento(evento.getId()))
                    .withRel("delete")
        );

        when(eventoService.getEventoById(evento.getId())).thenReturn(evento);
        when(eventoModelAssembler.toModel(evento)).thenReturn(eventoModel);

        mockMvc.perform(get("/eventos/{id}", evento.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(9L))
                .andExpect(jsonPath("$.nombre").value("Maratón Canina"))
                .andExpect(jsonPath("$.tipo_evento").value("Deporte"))
                .andExpect(jsonPath("$.descripcion").value("Carrera de resistencia para perros y dueños"))
                .andExpect(jsonPath("$.fecha").value("2026-01-25"))
                .andExpect(jsonPath("$.lugar").value("Parque Metropolitano"))
                .andExpect(jsonPath("$.participantes").value(400))
                .andExpect(jsonPath("$.organizador").value("Club Deportivo Canino"));
    }
}
