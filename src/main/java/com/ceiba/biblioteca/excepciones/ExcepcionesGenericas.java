package com.ceiba.biblioteca.excepciones;

public abstract class ExcepcionesGenericas extends RuntimeException {

    private String mensaje;

    protected ExcepcionesGenericas(String mensaje){
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
