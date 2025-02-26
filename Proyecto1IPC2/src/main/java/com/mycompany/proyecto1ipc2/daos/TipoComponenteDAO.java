/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos;

import com.mycompany.proyecto1ipc2.dtos.TipoComponente;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.servicios.Coneccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class TipoComponenteDAO implements BDCRUD<TipoComponente, Integer>{
    
    
    /**
     * recupera el id del tipo de componente en base al nombre que tenga
     * @param nombre nombre del componente
     * @return el id el tipo de componeten
     * @throws SQLException 
     */
    private int recuperarCodigoTipoComponente(String nombre) throws SQLException {
        String statement = "select idTipoComponente from TipoComponente where nombre = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement st = coneccion.prepareStatement(statement)) {
            st.setString(1, nombre);
            try (ResultSet result = st.executeQuery()) {
                result.next();
                return result.getInt("idTipoComponente");
            }
        }
    }

    /**
     * crea al tipo de componente con su nombre
     * @param nombre nombre del tipo de componente 
     * @return el id del tipo de componente
     * @throws SQLException 
     */
    public int crearTipoComponente(String nombre) throws SQLException {
        String statement = "insert ignore into TipoComponente(nombre) value (?)";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement st = coneccion.prepareStatement(statement)) {
            st.setString(1, nombre);
            st.executeUpdate();
            return recuperarCodigoTipoComponente(nombre);
        }

    }

    @Override
    public void insertar(TipoComponente entidad) throws InvalidDataException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<TipoComponente> encontrarPorID(Integer id) throws InvalidDataException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TipoComponente> obtenerTodo() {
        List<TipoComponente> tipos = new ArrayList<>();
        String statement = "select * from TipoComponente";
        try (Connection coneccion = Coneccion.getConeccion();
                Statement st = coneccion.createStatement();
                ResultSet result = st.executeQuery(statement)){
            while(result.next()){
                TipoComponente tipo = new TipoComponente();
                tipo.setId(result.getInt("idTipoComponente"));
                tipo.setNombre(result.getString("nombre"));
                tipos.add(tipo);
            }
        } catch (SQLException e) {
            System.out.println("error al conectar con la base de datos");
        }
        return tipos;
    }

    @Override
    public void actualizar(TipoComponente entidad) throws InvalidDataException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Integer id) throws InvalidDataException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
