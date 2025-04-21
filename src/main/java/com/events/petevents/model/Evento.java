package com.events.petevents.model;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    private Long id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private String hora_inicio;
    private String hora_fin;
    private String lugar;
    private Integer participantes;
}
