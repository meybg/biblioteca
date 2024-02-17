package com.ceiba.biblioteca.controller;

import com.ceiba.biblioteca.dto.PrestamoRequest;
import com.ceiba.biblioteca.dto.PrestamoResponse;
import com.ceiba.biblioteca.dto.SolicitudPrestamo;
import com.ceiba.biblioteca.service.interfaces.PrestamoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestamo")
public class PrestamoControlador {

    private final PrestamoService  prestamoService;

    public PrestamoControlador(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping()
    public final PrestamoResponse solicitarPrestamo(@RequestBody PrestamoRequest prestamoRequest){
        return prestamoService.solicitarPrestamo(prestamoRequest);
    }

    @GetMapping("/{id-prestamo}")
    public final SolicitudPrestamo solicitudPrestamo(@PathVariable("id-prestamo") int id){
        return prestamoService.obtenerSolicitudPrestamo(id);
    }
}
