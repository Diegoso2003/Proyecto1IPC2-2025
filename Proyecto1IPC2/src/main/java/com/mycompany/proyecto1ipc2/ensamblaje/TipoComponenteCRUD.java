/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ensamblaje;

import com.mycompany.proyecto1ipc2.daos.TipoComponenteDAO;
import com.mycompany.proyecto1ipc2.dtos.TipoComponente;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author rafael-cayax
 */
public class TipoComponenteCRUD {
    private TipoComponente tipo;
    private TipoComponenteDAO repositorio;
    
    public void crearTipoComponente(HttpServletRequest request) throws InvalidDataException {
        validarYObtenerDatos(request);
        crear();
    }

    private void validarYObtenerDatos(HttpServletRequest request) throws InvalidDataException {
        tipo = new TipoComponente();
        tipo.setNombre(request.getParameter("nombre"));
        if (tipo.getNombre() == null) {
            throw new InvalidDataException("ingrese un nombre valido");
        }
    }

    private void crear() throws InvalidDataException {
        repositorio = new TipoComponenteDAO();
        repositorio.insertar(tipo);
    }
    
}
