/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos;

import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public interface BDCRUD<T, ID> {
    
    public void insertar(T entidad) throws InvalidDataException;
    public Optional<T> encontrarPorID(ID id) throws InvalidDataException;
    public List<T> obtenerTodo();
    public void actualizar(T entidad) throws InvalidDataException;
    public void eliminar(ID id) throws InvalidDataException;
}
