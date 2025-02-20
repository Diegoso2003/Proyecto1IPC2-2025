/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.servicios;

import com.mycompany.proyecto1ipc2.daos.UsuarioDAO;
import com.mycompany.proyecto1ipc2.dtos.Usuario;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class Login {

    private Usuario usuario;

    /**
     * metodo para validar el inicio de la sesion
     *
     * @param request donde se envian los datos, como el nombre y la contraseña
     * @return la direccion de la pagina dependiendo del rol del usuario
     * @throws InvalidDataException si los datos ingresados son incorrectos
     */
    public String iniciarSesion(HttpServletRequest request) throws InvalidDataException {
        usuario = new Usuario();
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setContraseña(request.getParameter("contraseña"));
        validarNoNulos();
        Usuario usuario = verificarExistencia();
        validarEstado(usuario);
        compararContraseñas(usuario);
        request.getSession().setAttribute("Usuario", usuario.getNombre());
        return evaluarRol();
    }

    /**
     * sirve para verificar que tanto el nombre como la contraseña hayan sido
     * ingresados correctamente
     *
     * @throws InvalidDataException
     */
    private void validarNoNulos() throws InvalidDataException {
        if (!usuario.esValido()) {
            throw new InvalidDataException("Debe llenar todos los campos");
        }
    }

    /**
     * busca un usuario en base con el nombre ingresado para verificar si existe
     * o no
     *
     * @return al usuario con todos sus datos
     * @throws InvalidDataException en caso de que el usuario con el nombre
     * ingresado no exista
     */
    private Usuario verificarExistencia() throws InvalidDataException {
        UsuarioDAO user = new UsuarioDAO();
        Optional<Usuario> posibleUsuario = user.obtenerUsuarioPorNombre(this.usuario);
        Usuario usuario = posibleUsuario.orElseThrow(
                () -> new InvalidDataException("nombre o contraseña incorrectos"));
        return usuario;
    }

    /**
     * compara la contraseña ingresada con la guardada en la base de datos
     *
     * @param usuario el usuario con la contraseña hasheada
     * @throws InvalidDataException en caso de que las contraseñas no coincidan
     */
    private void compararContraseñas(Usuario usuario) throws InvalidDataException {
        Encriptador e = new Encriptador();
        if (!e.esValida(this.usuario.getContraseña(), usuario.getContraseña())) {
            throw new InvalidDataException("Nombre o Contraseña incorrectos");
        }
        this.usuario = usuario;
    }

    /**
     * dependiendo del rol del usuario asi es como se enviara a la pagina para
     * que pueda cumplir con sus labores
     *
     * @return
     */
    private String evaluarRol() {
        switch (usuario.getRol()) {
            case ADMINISTRADOR:
                return "/vista_administrador";
            case ENSAMBLADOR:
                return "/vista_ensamblador/crear_componente.jsp";
            case VENDEDOR:
                return "/vista_vendedor";
            default:
                return null;
        }
    }

    /**
     * metodo para verificar que el usuario no haya sido desactivado por algun 
     * administrador
     * @param usuario a evaluar estado
     * @throws InvalidDataException en caso de que el usuario haya sido desactivado
     */
    private void validarEstado(Usuario usuario) throws InvalidDataException {
        if (!usuario.isActivo()) {
            throw new InvalidDataException("Usuario desactivado");
        }
    }
}
