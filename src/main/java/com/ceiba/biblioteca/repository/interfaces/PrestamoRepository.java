package com.ceiba.biblioteca.repository.interfaces;

import com.ceiba.biblioteca.dto.SolicitudPrestamo;
import com.ceiba.biblioteca.dto.PrestamoResponse;

import java.time.LocalDate;

public interface PrestamoRepository {

    PrestamoResponse solicitarPrestamo(SolicitudPrestamo solicitudPrestamo);

    LocalDate obtenerFechaPrestamoPorUsuario(String identificacionUsuario);

    SolicitudPrestamo obtenerSolicitudPrestamo(int id);
}
