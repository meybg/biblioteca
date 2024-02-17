package com.ceiba.biblioteca.constantes;

public class ConstantesSql {

    public static final String CONSULTAR_MAX_FECHA_DEVOLUCION_POR_USUARIO = "SELECT MAX(p.fechaMaximaDevolucion) FROM prestamos p "
                                                                            + "WHERE p.identificacionUsuario = : identificacionUsuario ";


}
