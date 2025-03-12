/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.financiero;

import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComputadoraDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComponente;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora;
import com.mycompany.proyecto1ipc2.ensamblaje.TipoComponenteCRUD;
import com.mycompany.proyecto1ipc2.ensamblaje.TipoComputadoraCRUD;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author rafael-cayax
 */
public class GestionIndicacion {
    private TipoComponente tipoC;
    private TipoComputadora compu;

    public void agregarIndicacion(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        obtenerDatos(request);
        obtenerCantidad(request);
        TipoComputadoraDAO repositorio = new TipoComputadoraDAO();
        repositorio.insertarIndicacion(compu, tipoC);
    }
    
    private void obtenerCantidad(HttpServletRequest request) throws InvalidDataException{
        try {
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            if(cantidad <= 0){
                throw new InvalidDataException("ingresar una cantidad valida");
            }
            tipoC.setCantidad(cantidad);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("ingresar una cantida valida");
        }
    }
    
    private void obtenerDatos(HttpServletRequest request) throws InvalidDataException, NotFoundException{
        TipoComputadoraCRUD tipo = new TipoComputadoraCRUD();
        compu = tipo.obtenerEntidad(request);
        TipoComponenteCRUD comp = new TipoComponenteCRUD();
        comp.setIdNombre("componenteId");
        tipoC = comp.obtenerEntidad(request);
    }

    public void eliminarIndicacion(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        obtenerDatos(request);
        TipoComputadoraDAO repositorio = new TipoComputadoraDAO();
        repositorio.eliminarIndicacion(compu, tipoC);
    }

    public void actualizarIndicacion(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        obtenerDatos(request);
        obtenerCantidad(request);
        TipoComputadoraDAO repositorio = new TipoComputadoraDAO();
        repositorio.actualizarIndicacion(compu, tipoC);
    }
    
}
