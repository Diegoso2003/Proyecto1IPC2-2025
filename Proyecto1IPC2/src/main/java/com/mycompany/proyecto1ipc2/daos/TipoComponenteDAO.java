/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos;

import com.mycompany.proyecto1ipc2.servicios.Coneccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rafael-cayax
 */
public class TipoComponenteDAO {
    
    private Coneccion coneccion;
    
    private int recuperarCodigoTipoComponente(String nombre) throws SQLException {
        String statement = "select idTipoComponente from TipoComponente where nombre = ?";
        try (PreparedStatement st = coneccion.getConeccion().prepareStatement(statement)) {
            st.setString(1, nombre);
            try (ResultSet result = st.executeQuery()) {
                result.next();
                return result.getInt("idTipoComponente");
            }
        }
    }

    public int crearTipoComponente(Coneccion coneccion, String nombre) throws SQLException {
        String statement = "insert ignore into TipoComponente(nombre) value (?)";
        this.coneccion = coneccion;
        try (PreparedStatement st = coneccion.getConeccion().prepareStatement(statement)) {
            st.setString(1, nombre);
            st.executeUpdate();
            return recuperarCodigoTipoComponente(nombre);
        }

    }
}
