package com.ceiba.biblioteca.dto;

import java.time.LocalDate;

public class PrestamoResponse {

    private Integer id;
    private LocalDate fechaMaximaDevolucion;

    public PrestamoResponse() {

    }

    public PrestamoResponse(Integer id, LocalDate fechaMaximaDevolucion) {
        this.id = id;
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }

    public void setFechaMaximaDevolucion(LocalDate fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }
}
