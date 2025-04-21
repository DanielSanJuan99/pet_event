package com.events.petevents.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.validation.constraints.*;

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
    @NotNull(message = "El nombre del evento no puede ser nulo")
    @Size(min = 5, max = 100, message = "El nombre del evento debe tener entre 5 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El tipo de evento no puede estar vacío")
    @NotNull(message = "El tipo de evento no puede ser nulo")
    @Size(min = 5, max = 50, message = "El tipo de evento debe tener entre 5 y 50 caracteres")
    private String tipo_evento;

    @NotBlank(message = "La descripción no puede estar vacía")
    @NotNull(message = "La descripción no puede ser nula")
    @Size(min = 10, max = 500, message = "La descripción del evento debe tener entre 10 y 500 caracteres")
    private String descripcion;

    @NotBlank(message = "La fecha no puede estar vacía")
    @Future(message = "La fecha debe ser futura")
    @NotNull(message = "La fecha no puede ser nula")
    private String fecha;

    @NotBlank(message = "La hora de inicio no puede estar vacía")
    @FutureOrPresent(message = "La hora de inicio debe ser presente o futura")
    @Min(8) // Hora de inicio del evento debe ser mayor o igual a 9
    @Max(19) // Hora de inicio del evento debe ser menor o igual a 19
    @NotNull(message = "La hora de inicio no puede ser nula")
    private String hora_inicio;

    @NotBlank(message = "La hora de termino no puede estar vacía")
    @FutureOrPresent(message = "La hora de termino debe ser presente o futura")
    @Min(12) // Hora de termino del evento debe ser mayor o igual a 12
    @Max(23) // Hora de termino del evento debe ser menor o igual a 23
    @NotNull(message = "La hora de termino no puede ser nula")
    private String hora_fin;

    @NotBlank(message = "El lugar no puede estar vacío")
    @Size(min = 5, max = 200, message = "El lugar del evento debe tener entre 5 y 200 caracteres")
    private String lugar;

    @NotNull(message = "La cantidad de participantes no puede ser nula")
    @Positive(message = "La cantidad de participantes debe ser un número positivo")
    @Min(1) // La cantidad de participantes debe ser mayor o igual a 1
    @Max(1000) // La cantidad de participantes debe ser menor o igual a 1000
    @NotNull(message = "La cantidad de participantes no puede ser nula")
    private Integer participantes;

    @NotNull(message = "El organizador no puede ser nulo")
    @NotBlank(message = "El organizador no puede estar vacío")  
    @Size(min = 5, max = 100, message = "El organizador del evento debe tener entre 5 y 100 caracteres")
    private String organizador;
}
