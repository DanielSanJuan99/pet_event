package com.events.petevents.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.events.petevents.model.Evento;
import com.events.petevents.controller.EventoController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EventoModelAssembler implements RepresentationModelAssembler<Evento, EntityModel<Evento>> {

    @Override
    public EntityModel<Evento> toModel(Evento evento) {
        return EntityModel.of(
                evento,

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
                        .withRel("delete"));
    }
    
}
