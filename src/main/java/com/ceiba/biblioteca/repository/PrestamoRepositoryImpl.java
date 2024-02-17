package com.ceiba.biblioteca.repository;

import com.ceiba.biblioteca.constantes.ConstantesSql;
import com.ceiba.biblioteca.dto.SolicitudPrestamo;
import com.ceiba.biblioteca.dto.PrestamoResponse;
import com.ceiba.biblioteca.model.Prestamo;
import com.ceiba.biblioteca.repository.interfaces.PrestamoRepository;
import com.ceiba.biblioteca.utilitarios.ControladorFechas;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Repository
public class PrestamoRepositoryImpl implements PrestamoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public PrestamoResponse solicitarPrestamo(SolicitudPrestamo solicitudPrestamo) {
        Prestamo prestamo = new Prestamo(solicitudPrestamo.getIsbn(), solicitudPrestamo.getIdentificacionUsuario(), solicitudPrestamo.getTipoUsuario(),
                solicitudPrestamo.getFechaMaximaDevolucion());
        em.persist(prestamo);
        PrestamoResponse prestamoResponse = new PrestamoResponse();
        prestamoResponse.setId(prestamo.getId());
        prestamoResponse.setFechaMaximaDevolucion(solicitudPrestamo.getFechaMaximaDevolucion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        return prestamoResponse;
    }

    @Override
    public LocalDate obtenerFechaPrestamoPorUsuario(String identificacionUsuario) {
        Query query = em.createQuery(ConstantesSql.CONSULTAR_MAX_FECHA_DEVOLUCION_POR_USUARIO);
        query.setParameter("identificacionUsuario",identificacionUsuario);
        Date maximaFecha = (Date) query.getSingleResult();
        if(maximaFecha != null) {
            return maximaFecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else {
            return ControladorFechas.restarDiasAFecha(LocalDate.now(),1);
        }
    }

    @Override
    public SolicitudPrestamo obtenerSolicitudPrestamo(int id) {
        Prestamo prestamo = em.find(Prestamo.class, id);
        SolicitudPrestamo solicitudPrestamo = new SolicitudPrestamo();
        solicitudPrestamo.setId(prestamo.getId());
        solicitudPrestamo.setIsbn(prestamo.getIsbn());
        solicitudPrestamo.setIdentificacionUsuario(prestamo.getIdentificacionUsuario());
        solicitudPrestamo.setTipoUsuario(prestamo.getTipoUsuario());
        solicitudPrestamo.setFechaMaximaDevolucion(prestamo.getFechaMaximaDevolucion());
        return solicitudPrestamo;
    }

}
