package com.events.petevents.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @NotNull(message = "El id no puede ser nulo")
    @Positive(message = "El id debe ser un número positivo")
    private Long id;

    @NotBlank(message = "El nombre del evento no puede estar vacío")
    @Size(min = 5, max = 100, message = "El nombre del evento debe tener entre 5 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El tipo de evento no puede estar vacío")
    @Size(min = 5, max = 50, message = "El tipo de evento debe tener entre 5 y 50 caracteres")
    private String tipo_evento;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 10, max = 500, message = "La descripción del evento debe tener entre 10 y 500 caracteres")
    private String descripcion;

    @Future(message = "La fecha debe ser futura")
    private LocalDate fecha;

    @NotBlank(message = "El lugar no puede estar vacío")
    @Size(min = 5, max = 200, message = "El lugar del evento debe tener entre 5 y 200 caracteres")
    private String lugar;

    @Positive(message = "La cantidad de participantes debe ser un número positivo")
    @Min(1) // La cantidad de participantes debe ser mayor o igual a 1
    @Max(1000) // La cantidad de participantes debe ser menor o igual a 1000
    private Integer participantes;

    @NotBlank(message = "El organizador no puede estar vacío")  
    @Size(min = 5, max = 100, message = "El organizador del evento debe tener entre 5 y 100 caracteres")
    private String organizador;
}
