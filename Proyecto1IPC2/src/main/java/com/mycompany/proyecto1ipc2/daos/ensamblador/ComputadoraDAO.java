/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos.ensamblador;

import com.mycompany.proyecto1ipc2.daos.BDCRUD;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import com.mycompany.proyecto1ipc2.servicios.Coneccion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class ComputadoraDAO extends BDCRUD<Computadora, Integer>{

    @Override
    public void insertar(Computadora entidad) throws InvalidDataException {
        String query = "INSERT INTO Computadora(fechaEnsamblaje, ensamblador, precioFabricacion, precioVenta, "
                + "idTipo, estado) VALUES(?, ?, ?, 0.00, ?, 'ENSAMBLADA')";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, Date.valueOf(entidad.getFechaEnsamblaje()));
            statement.setString(2, entidad.getEnsamblador());
            statement.setDouble(3, entidad.getPrecioFabricacion());
            statement.setInt(4, entidad.getTipo().getIdTipo());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entidad.setIdComputadora(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException("datos ingresado no validos" + e);
        }
    }

    @Override
    public Optional<Computadora> encontrarPorID(Integer id) throws InvalidDataException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Computadora> obtenerTodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Computadora entidad) throws InvalidDataException, NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Integer id) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
