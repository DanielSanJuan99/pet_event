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
        eventos.add(new Evento(1L, "Feria", "Feria recreativa para perros", "2023-10-01", "15:00", "19:00", "Mall Plaza Norte", 20));
        eventos.add(new Evento(2L, "Competencia", "Competencia canina patrocinada por Dog Show", "2023-12-05", "09:00", "17:00", "Parque O'higgins", 200));
        eventos.add(new Evento(3L, "Feria", "Feria de venta de alimentos naturales para mascotas", "2024-02-19", "11:00", "18:00", "Estadio Nacional", 100));
        eventos.add(new Evento(4L, "Adopcion", "Evento de adopcion de mascotas", "2024-05-09", "14:00", "21:00", "Estadio Nacional", 200));
        eventos.add(new Evento(5L, "Charla", "Charla sobre cuidado y tenencia responsable", "2024-08-03", "12:00", "20:00", "Parque Bicentenario", 150));
    }

    public List<Evento> getAllEvents() {
        return eventos;
    }

    public Optional<Evento> getEventoById(Long id) {
        return eventos.stream().filter(e -> e.getId().equals(id)).findFirst();
    }
}
