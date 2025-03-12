/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ensamblaje;

import com.mycompany.proyecto1ipc2.CRUD;
import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComputadoraDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author rafael-cayax
 */
public class TipoComputadoraCRUD extends CRUD<TipoComputadora>{

    public TipoComputadoraCRUD() {
        super("Tipo de computadora", new TipoComputadoraDAO());
    }

    @Override
    protected void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException {
        entidad = new TipoComputadora();
        try {
            entidad.setNombre(request.getParameter("nombre").trim().replaceAll("\\s+", " "));
            entidad.setPrecio(Double.parseDouble(request.getParameter("precio")));
            if (!entidad.esValido()) {
                throw new InvalidDataException("ingresar datos validos");
            }
            if (actu) {
                entidad.setIdTipo(Integer.parseInt(request.getParameter("id")));
            }
        } catch (NullPointerException | NumberFormatException e) {
            throw new InvalidDataException("ingresar datos validos");
        }
    }
    
}
