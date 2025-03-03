/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ensamblaje;

import com.mycompany.proyecto1ipc2.CRUD;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author rafael-cayax
 */
public class EnsamblajeCRUD extends CRUD<Computadora>{

    @Override
    protected void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException {
        
    }
    
}
