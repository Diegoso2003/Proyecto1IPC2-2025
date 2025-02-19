/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.servicios;

import com.mycompany.proyecto1ipc2.dtos.Usuario;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author rafael-cayax
 */
public class Login {
    private Usuario usuario;

    /**
     * metodo para validar el inicio de la sesion
     * @param request donde se envian los datos, como el nombre y la contraseña
     * @return la direccion de la pagina dependiendo del rol del usuario
     * @throws InvalidDataException si los datos ingresados son incorrectos
     */
    public String iniciarSesion(HttpServletRequest request) throws InvalidDataException {
        usuario = new Usuario();
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setContraseña(request.getParameter("contraseña"));
        validarNoNulos();
        return null;
    }

    /**
     * sirve para verificar que tanto el nombre como la contraseña hayan sido
     * ingresados correctamente
     * @throws InvalidDataException 
     */
    private void validarNoNulos() throws InvalidDataException {
        if (!usuario.esValido()) {
            throw new InvalidDataException("Debe llenar todos los campos");
        }
    }
}
