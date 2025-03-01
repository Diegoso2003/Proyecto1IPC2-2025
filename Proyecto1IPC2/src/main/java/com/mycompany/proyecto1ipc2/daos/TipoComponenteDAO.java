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
public class TipoComponenteDAO extends BDCRUD<TipoComponente, Integer>{
    
    @Override
    public void insertar(TipoComponente entidad) throws InvalidDataException {
        String statement = "INSERT INTO TipoComponente(nombre) value(?)";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement st = coneccion.prepareStatement(statement)){
            st.setString(1, entidad.getNombre().trim().replaceAll("\\s+", " "));
            st.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new InvalidDataException("tipo de componente con nombre: '" + entidad.getNombre() + ""
                        + "' ya esta registrado en el sistema");
            }
            throw new InvalidDataException("Ingrese un nombre valido");
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
        String statement = "UPDATE TipoComponente SET nombre = ? WHERE idTipoComponente = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement st = coneccion.prepareStatement(statement)){
            st.setString(1, entidad.getNombre().trim().replaceAll("\\s+", " "));
            st.setInt(2, entidad.getId());
            if (st.executeUpdate() <= 0) {
                throw new InvalidDataException("tipo de componente con id: '" +  entidad.getId() + "'"
                        + " no encontrado");
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new InvalidDataException("tipo de componente con nombre: '" + entidad.getNombre() + ""
                        + "' ya esta registrado en el sistema");
            }
            throw new InvalidDataException("Ingrese un nombre valido");
        }
    }

    @Override
    public void eliminar(Integer id) throws NotFoundException {
        String statement = "DELETE FROM TipoComponente WHERE idTipoComponente = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement st = coneccion.prepareStatement(statement)){
            st.setInt(1, id);
            if (st.executeUpdate() <= 0) {
                throw new NotFoundException("No se encontro el componente con el id: "
                        + "'" + id + "'");
            }
        } catch (SQLException e) {
            throw new NotFoundException("error al conectar con la base de datos");
        }
    }
    
}
