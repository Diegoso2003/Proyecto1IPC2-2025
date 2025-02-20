/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos;

import com.mycompany.proyecto1ipc2.dtos.Usuario;
import com.mycompany.proyecto1ipc2.enums.EnumRol;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.servicios.Coneccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class UsuarioDAO {

    /**
     * obtiene todos los datos del usuario usando el nombre del mismo, en caso 
     * de no existir un usuario con ese nombre se le envia un optional vacio
     * @param usuario el nombre del usuario
     * @return un optional con el usuario
     * @throws InvalidDataException en caso de enviar un parametro incorrecto
     */
    public Optional<Usuario> obtenerUsuarioPorNombre(Usuario usuario) throws InvalidDataException {
        Coneccion c = new Coneccion();
        String sql = "SELECT u.nombre, u.contraseña, u.estado, r.nombre as rol FROM Usuario u "
                + "inner join Rol r on idRol = rol WHERE u.nombre = ?";
        try (Connection coneccion = c.getConeccion(); PreparedStatement st = coneccion.prepareStatement(sql)) {
            st.setString(1, usuario.getNombre());
            try (ResultSet result = st.executeQuery()) {
                if (result.next()) {
                    Usuario usuario2 = new Usuario();
                    usuario2.setNombre(result.getString("nombre"));
                    usuario2.setContraseña(result.getString("contraseña"));
                    usuario2.setRol(EnumRol.valueOf(result.getString("rol")));
                    usuario2.setActivo(result.getBoolean("estado"));
                    Optional<Usuario> user = Optional.of(usuario2);
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException("nombre o contraseña incorrectos");
        }
        return Optional.empty();
    }
}
