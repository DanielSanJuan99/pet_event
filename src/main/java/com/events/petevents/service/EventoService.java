package com.events.petevents.service;

import com.events.petevents.model.Evento;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    
    private List<Evento> eventos = new ArrayList<>();

    public EventoService() {
        eventos.add(new Evento(1L, "Feria", "Concierto de rock", "2023-10-01", "18:00", "21:00", "Estadio Nacional", 100));
        eventos.add(new Evento(2L, "Competencia", "Conferencia sobre tecnología", "2023-10-05", "09:00", "17:00", "Centro de Convenciones", 200));
        eventos.add(new Evento(3L, "Feria", "Concierto de rock", "2023-10-01", "18:00", "21:00", "Estadio Nacional", 100));
        eventos.add(new Evento(4L, "Feria", "Concierto de rock", "2023-10-01", "18:00", "21:00", "Estadio Nacional", 100));
    }

    public List<Evento> getEventos() {
        return eventos;
    }
}
