/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos;

import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 * @param <T> clase de la entidad
 * @param <ID> tipo de dato para el id
 */
public abstract class BDCRUD<T, ID> {

    protected boolean ordenar = false;
    protected String orden;
    public abstract void insertar(T entidad) throws InvalidDataException;
    public abstract Optional<T> encontrarPorID(ID id) throws InvalidDataException;
    public abstract List<T> obtenerTodo();
    public abstract void actualizar(T entidad) throws InvalidDataException, NotFoundException;
    public abstract void eliminar(ID id) throws NotFoundException;

    public void setOrdenar(boolean orden) {
        this.ordenar = orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }
    
}
