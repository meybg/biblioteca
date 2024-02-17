package com.ceiba.biblioteca.config;

import com.ceiba.biblioteca.excepciones.BibliotecaExcepcion;
import com.ceiba.biblioteca.excepciones.ExcepcionesGenericas;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {
    private HttpServletRequest httpServletRequest;

    public ErrorHandler(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorResponse> handle(Throwable ex){
        return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    public ResponseEntity<ApiErrorResponse> buildResponseError(HttpStatus httpStatus, Throwable ex){
        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getMessage());
        return new ResponseEntity<>(apiErrorResponse, httpStatus);
    }

    @ExceptionHandler({
            BibliotecaExcepcion.class,
    })
    public ResponseEntity<ApiErrorResponse> handle(ExcepcionesGenericas ex) {
        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getMensaje());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }


}
