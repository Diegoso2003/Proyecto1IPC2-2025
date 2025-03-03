/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2;

import com.mycompany.proyecto1ipc2.daos.BDCRUD;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public abstract class CRUD<T> {

    protected T entidad;
    protected boolean actu = false;
    protected String nombre;
    protected BDCRUD repositorio;

    public void crearEntidad(HttpServletRequest request) throws InvalidDataException {
        obtenerYValidarDatos(request);
        repositorio.insertar(entidad);
    }

    public void eliminarEntidad(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        int codigo = obtenerID(request);
        repositorio.eliminar(codigo);
    }

    public T actualizarEntidad(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        actu = true;
        obtenerYValidarDatos(request);
        repositorio.actualizar(entidad);
        return entidad;
    }

    public T obtenerEntidad(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        int codigo = obtenerID(request);
        Optional<T> posibleEntidad = repositorio.encontrarPorID(codigo);
        entidad = posibleEntidad.orElseThrow(() -> new NotFoundException( nombre + " con ID: '" + codigo + "' no encontrado"));
        return entidad;
    }

    protected int obtenerID(HttpServletRequest request) throws InvalidDataException {
        try {
            int codigo = Integer.parseInt(request.getParameter("id"));
            return codigo;
        } catch (NumberFormatException | NullPointerException e) {
            throw new InvalidDataException("ingresar un id valido");
        }
    }
    
    public List<T> obtenerPorOrden(HttpServletRequest request) throws InvalidDataException{
        String orden = request.getParameter("orden");
        if (orden == null || orden.isEmpty()) {
            throw new InvalidDataException("Intente de nuevo");
        }
        if (!(orden.equals("DESC") || orden.equals("ASC"))) {
            throw new InvalidDataException("Intente de nuevo");
        }
        repositorio.setOrdenar(true);
        repositorio.setOrden(orden);
        return repositorio.obtenerTodo();
    }

    protected abstract void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException;

    public T getEntidad() {
        return entidad;
    }
    
}
