package com.events.petevents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.petevents.model.Evento;

// MANEJO DE CRUD CON JPA
public interface EventoRepository extends JpaRepository<Evento, Long> {
    // No es necesario agregar métodos adicionales, ya que JpaRepository proporciona
    // métodos CRUD básicos.


    
}
