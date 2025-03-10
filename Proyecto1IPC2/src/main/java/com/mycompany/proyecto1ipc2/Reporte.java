/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2;

import com.mycompany.proyecto1ipc2.daos.ConsultaDAO;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author rafael-cayax
 */
public class Reporte<T> {
    private Consulta consulta;
    protected ConsultaDAO<T> repositorio;

    public Reporte(ConsultaDAO<T> repositorio) {
        this.repositorio = repositorio;
    }

    
    public T obtenerDatosConsulta(HttpServletRequest request) throws InvalidDataException{
        consulta = new Consulta();
        consulta.setFechaInicio(obtenerFechaInicio(request));
        consulta.setFechaFin(obtenerFechaFin(request));
        return repositorio.realizarConsulta(consulta);
    }
    
    private LocalDate obtenerFecha(HttpServletRequest request, String tipo){
        try {
            LocalDate fecha = LocalDate.parse(request.getParameter(tipo));
            request.setAttribute(tipo, fecha);
            return fecha;
        } catch (DateTimeParseException | NullPointerException e) {
            return null;
        }
    }
    
    private LocalDate obtenerFechaFin(HttpServletRequest request){
        return obtenerFecha(request, "fechaFin");
    }
    
    private LocalDate obtenerFechaInicio(HttpServletRequest request){
        return obtenerFecha(request, "fechaInicio");
    }
    
}
