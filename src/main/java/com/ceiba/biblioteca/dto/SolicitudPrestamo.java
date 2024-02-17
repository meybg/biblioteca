package com.ceiba.biblioteca.dto;

import com.ceiba.biblioteca.constantes.Constantes;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SolicitudPrestamo {

    private Integer id;
    private String isbn;
    private String identificacionUsuario;
    private Integer tipoUsuario;

    private Date fechaMaximaDevolucion;

    public SolicitudPrestamo() {

    }

    public SolicitudPrestamo(Integer id, String isbn, String identificacionUsuario, Integer tipoUsuario, Date fechaMaximaDevolucion) {
        this.id = id;
        this.isbn = isbn;
        this.identificacionUsuario = identificacionUsuario;
        this.tipoUsuario = tipoUsuario;
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constantes.FORMATO_FECHA_UNO)
    public Date getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }

    public void setFechaMaximaDevolucion(Date fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }
}
