/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ensamblaje;

import com.mycompany.proyecto1ipc2.CRUD;
import com.mycompany.proyecto1ipc2.daos.ensamblador.ComponenteDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.ComputadoraDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.DetalleEnsamblajeDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComputadoraDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Componente;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.DetalleEnsamblaje;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComponente;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora;
import com.mycompany.proyecto1ipc2.enums.EnumEstadoCompu;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class ComputadoraCRUD extends CRUD<Computadora>{

    public ComputadoraCRUD() {
        super("Computadora", new ComputadoraDAO());
    }
    
    @Override
    public void crearEntidad(HttpServletRequest request) throws InvalidDataException {
        TipoComputadoraCRUD compu = new TipoComputadoraCRUD();
        try {
            request.setAttribute("tipo_computadora", compu.obtenerEntidad(request));
        } catch (NotFoundException ex) {
            throw new InvalidDataException(ex.getMessage());
        }
        obtenerYValidarDatos(request);
        Ensamblaje ensamblaje = new Ensamblaje(entidad);
        ensamblaje.ensamblar();
        repositorio.insertar(entidad);
        ensamblaje.actualizarRepositorios();
        entidad.setPrecioFabricacion((entidad.getPrecioFabricacion() * 100.0)/100.0);
    }

    @Override
    protected void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException {
        entidad = new Computadora();
        try {
            entidad.setFechaEnsamblaje(LocalDate.parse(request.getParameter("fecha")));
            entidad.setEnsamblador(request.getSession().getAttribute("Usuario").toString());
            entidad.setEstado(EnumEstadoCompu.ENSAMBLADA);
            TipoComputadora tipo = new TipoComputadora();
            tipo.setIdTipo(obtenerID(request));
            entidad.setTipo(tipo);
        } catch (NullPointerException | DateTimeParseException e) {
            throw new InvalidDataException("Ingrese correctamente los datos" + e);
        }
    }

    

    public List<Computadora> obtenerComputadoras(HttpServletRequest request) {
        try {
            return obtenerPorOrden(request);
        } catch (InvalidDataException ex) {
            return repositorio.obtenerTodo();
        }
    }
    
    
}
