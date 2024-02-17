package com.ceiba.biblioteca.service.interfaces;

import com.ceiba.biblioteca.dto.PrestamoRequest;
import com.ceiba.biblioteca.dto.PrestamoResponse;
import com.ceiba.biblioteca.dto.SolicitudPrestamo;

public interface PrestamoService {

    PrestamoResponse solicitarPrestamo(PrestamoRequest prestamoRequest);

    SolicitudPrestamo obtenerSolicitudPrestamo(int id);


}
