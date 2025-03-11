/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.financiero;

import com.mycompany.proyecto1ipc2.CRUD;
import com.mycompany.proyecto1ipc2.daos.BDCRUD;
import com.mycompany.proyecto1ipc2.daos.UsuarioDAO;
import com.mycompany.proyecto1ipc2.dtos.Usuario;
import com.mycompany.proyecto1ipc2.enums.EnumRol;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import com.mycompany.proyecto1ipc2.servicios.Encriptador;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author rafael-cayax
 */
public class UsuarioCRUD extends CRUD<Usuario> {

    public UsuarioCRUD() {
        super("usuario", new UsuarioDAO());
    }

    @Override
    protected void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        entidad = new Usuario();
        entidad.setNombre(request.getParameter("nombre"));
        entidad.setContraseña(request.getParameter("contraseña"));
        entidad.setConfirmacionContraseña(request.getParameter("confirmacionContraseña"));
        try {
            entidad.setRol(EnumRol.valueOf(request.getParameter("rol")));
        } catch (Exception e) {
            throw new InvalidDataException("ingresar valores validos");
        }
        if (!entidad.esCreacionValida()) {
            throw new InvalidDataException("ingresar valores validos");
        }
        Encriptador encriptador = new Encriptador();
        entidad.setContraseña(encriptador.encriptar(entidad.getContraseña()));
    }
    
    public void alternarEstado(HttpServletRequest request) throws NotFoundException, InvalidDataException{
        String nombre = request.getParameter("nombre");
        String actual = (String)request.getSession().getAttribute("Usuario");
        if (actual.equals(nombre)) {
            throw new InvalidDataException("Acción no permitida: No puedes modificar tu propio estado. "
                    + "Por favor, contacta a otro administrador para realizar este cambio.");
        }
        repositorio.eliminar(nombre);
    }
    
}
