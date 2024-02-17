package com.ceiba.biblioteca.service;

import com.ceiba.biblioteca.constantes.Constantes;
import com.ceiba.biblioteca.dto.SolicitudPrestamo;
import com.ceiba.biblioteca.dto.PrestamoRequest;
import com.ceiba.biblioteca.dto.PrestamoResponse;
import com.ceiba.biblioteca.excepciones.BibliotecaExcepcion;
import com.ceiba.biblioteca.repository.interfaces.PrestamoRepository;
import com.ceiba.biblioteca.service.interfaces.PrestamoService;
import com.ceiba.biblioteca.utilitarios.ControladorFechas;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public PrestamoResponse solicitarPrestamo(PrestamoRequest prestamoRequest) {
        verificarUsuario(prestamoRequest.getTipoUsuario());
        SolicitudPrestamo solicitudPrestamo = new SolicitudPrestamo();
        solicitudPrestamo.setIsbn(prestamoRequest.getIsbn());
        solicitudPrestamo.setIdentificacionUsuario(prestamoRequest.getIdentificacionUsuario());
        solicitudPrestamo.setTipoUsuario(prestamoRequest.getTipoUsuario());
        solicitudPrestamo.setFechaMaximaDevolucion(ControladorFechas.sumarDiasHabilesAFecha(LocalDate.now(), obtenerDiasPorUsuario(prestamoRequest.getTipoUsuario())));
        if (prestamoRequest.getTipoUsuario().equals(Constantes.USUARIO_INVITADO)) {
            validarPrestamoInvitado(prestamoRequest);
        }
        return prestamoRepository.solicitarPrestamo(solicitudPrestamo);
    }

    @Override
    public SolicitudPrestamo obtenerSolicitudPrestamo(int id) {
        return prestamoRepository.obtenerSolicitudPrestamo(id);
    }

    private Integer obtenerDiasPorUsuario(int tipoUsuario) {
        Integer dias;
        switch (tipoUsuario) {
            case Constantes.USUARIO_AFILIADO:
                dias = Constantes.NUM_DIAS_USUARIO_AFILIADO;
                break;
            case Constantes.USUARIO_EMPLEADO:
                dias = Constantes.NUM_DIAS_USUARIO_EMPLEADO;
                break;
            case Constantes.USUARIO_INVITADO:
                dias = Constantes.NUM_DIAS_USUARIO_INVITADO;
                break;
            default:
                dias = Constantes.NUM_DIAS_DEFAULT;
        }
        return dias;
    }

    private void validarPrestamoInvitado(PrestamoRequest prestamoRequest) {
        LocalDate fechaMaximaDevolucion = prestamoRepository.obtenerFechaPrestamoPorUsuario(prestamoRequest.getIdentificacionUsuario());
        if (fechaMaximaDevolucion != null && fechaMaximaDevolucion.isAfter(LocalDate.now())) {
            throw new BibliotecaExcepcion("El usuario con identificación " + prestamoRequest.getIdentificacionUsuario()
                    + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
        }
    }

    private void verificarUsuario(Integer tipoUsuario) {
        if (tipoUsuario != null && tipoUsuario >= Constantes.USUARIO_INVALIDO) {
            throw new BibliotecaExcepcion("Tipo de usuario no permitido en la biblioteca");
        }
    }

}
