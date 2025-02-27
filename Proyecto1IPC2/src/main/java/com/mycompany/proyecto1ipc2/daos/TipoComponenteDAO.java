/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos;

import com.mycompany.proyecto1ipc2.dtos.TipoComponente;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
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
    
    @Override
    public void insertar(TipoComponente entidad) throws InvalidDataException {
        String statement = "INSERT ignore into TipoComponente(nombre) value(?)";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement st = coneccion.prepareStatement(statement)){
            st.setString(1, entidad.getNombre());
            if (st.executeUpdate() <= 0) {
                throw new InvalidDataException("el tipo de componente: '" + entidad.getNombre()
                +"' ya esta registrado en el sistema");
            }
        } catch (SQLException e) {
            throw new InvalidDataException("el nombre: '" + entidad.getNombre() + "' no es valido");
        }
    }

    @Override
    public Optional<TipoComponente> encontrarPorID(Integer id) throws InvalidDataException {
        String statement = "SELECT * FROM TipoComponente WHERE idTipoComponente = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement st = coneccion.prepareStatement(statement)){
            st.setInt(1, id);
            try(ResultSet result = st.executeQuery()){
                if (result.next()) {
                    TipoComponente tipo = new TipoComponente();
                    tipo.setId(result.getInt("idTipoComponente"));
                    tipo.setNombre(result.getString("nombre"));
                    return Optional.of(tipo);
                }
            }
        } catch (SQLException e) {
        }
        return Optional.empty();
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
    public void eliminar(Integer id) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
