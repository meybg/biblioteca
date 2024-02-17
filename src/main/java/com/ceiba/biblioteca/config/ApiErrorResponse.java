package com.ceiba.biblioteca.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiErrorResponse {

    @JsonProperty
    private String mensaje;

    public ApiErrorResponse(String mensaje) {
        this.mensaje = mensaje;
    }
}
