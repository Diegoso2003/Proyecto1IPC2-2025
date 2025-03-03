/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.servicios;

import com.mycompany.proyecto1ipc2.daos.ensamblador.ComponenteDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComponenteDAO;
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
        String nombre = request.getParameter("nombre");
        String contraseña = request.getParameter("contraseña");
        validarNoNulos(nombre, contraseña);
        usuario = verificarExistencia(nombre.trim());
        compararContraseñas(contraseña);
        request.getSession().setAttribute("Usuario", usuario.getNombre());
        return evaluarRol(request);
    }

    /**
     * sirve para verificar que tanto el nombre como la contraseña hayan sido
     * ingresados correctamente
     *
     * @throws InvalidDataException
     */
    private void validarNoNulos(String nombre, String contraseña) throws InvalidDataException {
        if (nombre == null || contraseña == null || nombre.isBlank() || contraseña.isBlank()) {
            throw new InvalidDataException("Debe Ingresar ambos datos correctamente");
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
    private Usuario verificarExistencia(String nombre) throws InvalidDataException {
        UsuarioDAO user = new UsuarioDAO();
        Optional<Usuario> posibleUsuario = user.encontrarPorID(nombre);
        Usuario usuario2 = posibleUsuario.orElseThrow(
                () -> new InvalidDataException("nombre o contraseña incorrectos"));
        return usuario2;
    }

    /**
     * compara la contraseña ingresada con la guardada en la base de datos
     *
     * @param usuario el usuario con la contraseña hasheada
     * @throws InvalidDataException en caso de que las contraseñas no coincidan
     */
    private void compararContraseñas(String contraseña) throws InvalidDataException {
        Encriptador e = new Encriptador();
        if (!e.esValida(contraseña, usuario.getContraseña())) {
            throw new InvalidDataException("Nombre o Contraseña incorrectos");
        }
    }

    /**
     * dependiendo del rol del usuario asi es como se enviara a la pagina para
     * que pueda cumplir con sus labores
     *
     * @return
     */
    private String evaluarRol(HttpServletRequest request) {
        switch (usuario.getRol()) {
            case ADMINISTRADOR:
                return "/vista_administrador";
            case ENSAMBLADOR:
                TipoComponenteDAO tipo = new TipoComponenteDAO();
                ComponenteDAO componentes = new ComponenteDAO();
                request.setAttribute("tipos", tipo.obtenerTodo());
                request.setAttribute("componentes", componentes.obtenerTodo());
                return "/vista_ensamblador/lista_componentes.jsp";
            case VENDEDOR:
                return "/vista_vendedor";
            default:
                return null;
        }
    }

}
