/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ventas;

import com.mycompany.proyecto1ipc2.CRUD;
import com.mycompany.proyecto1ipc2.daos.ventas.ClienteDAO;
import com.mycompany.proyecto1ipc2.dtos.ventas.Cliente;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author rafael-cayax
 */
public class ClienteCRUD extends CRUD<Cliente> {

    public ClienteCRUD() {
        repositorio = new ClienteDAO();
        nombre = "cliente";
    }

    @Override
    protected void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException{
        try {
            entidad = new Cliente();
            entidad.setNombre(request.getParameter("nombre"));
            entidad.setDireccion(request.getParameter("direccion"));
            entidad.setNit(Integer.parseInt(request.getParameter("nit")));
        } catch (NumberFormatException | NullPointerException e) {
            throw new InvalidDataException("Ingresar correctament los datos solicitados");
        }
    }

}
