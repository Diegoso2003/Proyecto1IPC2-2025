/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ensamblaje;

import com.mycompany.proyecto1ipc2.CRUD;
import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComponenteDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComponente;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author rafael-cayax
 */
public class TipoComponenteCRUD extends CRUD<TipoComponente>{

    public TipoComponenteCRUD() {
        nombre = "tipo de componente";
        repositorio = new TipoComponenteDAO();
    }

    @Override
    protected void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException {
        entidad = new TipoComponente();
        entidad.setNombre(request.getParameter("nombre"));
        if (!entidad.esValido()) {
            throw new InvalidDataException("ingrese un nombre valido");
        }
        if (actu) {
            entidad.setId(obtenerID(request));
        }
    }

}
