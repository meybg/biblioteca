package com.ceiba.biblioteca.utilitarios;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ControladorFechas {

    public static Date sumarDiasHabilesAFecha(LocalDate fecha, Integer numDias){
        int diasASumar = 0;
        while (diasASumar < numDias) {
            fecha = fecha.plusDays(1);
            if (fecha.getDayOfWeek() != DayOfWeek.SATURDAY && fecha.getDayOfWeek() != DayOfWeek.SUNDAY) {
                diasASumar++;
            }

        }
        return Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate restarDiasAFecha(LocalDate fecha, Integer numDias){
        return fecha.minusDays(numDias);
    }
}
