package com.events.petevents.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder({"status", "cantidad", "timestamp", "data"})

public class ReponseWrapper<T> {

    private String status;
    private int cantidad;  
    private LocalDateTime timestamp;
    private List<T> data;

    public ReponseWrapper(String status, int cantidad, LocalDateTime timestamp, List<T> data) {
        this.status = status;
        this.cantidad = cantidad;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }


    
}
