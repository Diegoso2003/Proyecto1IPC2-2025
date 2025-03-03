/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos.ensamblador;

import com.mycompany.proyecto1ipc2.daos.BDCRUD;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora;
import com.mycompany.proyecto1ipc2.enums.EnumEstadoCompu;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import com.mycompany.proyecto1ipc2.servicios.Coneccion;
import java.sql.Connection;
import java.sql.Date;
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
        List<Computadora> computadoras = new ArrayList<>();
        String statement = "SELECT * FROM Computadora c INNER JOIN TipoComputadora t ON c.idTipo = t.idTipo";
        statement += ordenar ? " ORDER BY fechaEnsamblaje " + orden: "";
        try (Connection c = Coneccion.getConeccion(); 
                Statement st = c.createStatement(); 
                ResultSet result = st.executeQuery(statement)) {
            while (result.next()) {
                Computadora computadora = new Computadora();
                computadora.setIdComputadora(result.getInt(1));
                computadora.setFechaEnsamblaje(result.getDate(2).toLocalDate());
                computadora.setEnsamblador(result.getString(3));
                computadora.setPrecioFabricacion(result.getDouble(4));
                TipoComputadora tipo = new TipoComputadora();
                tipo.setIdTipo(6);
                tipo.setNombre(result.getString(9));
                computadora.setTipo(tipo);
                computadora.setEstado(EnumEstadoCompu.valueOf(result.getString(7)));
                computadoras.add(computadora);
            }
        } catch (SQLException e) {
        }
        return computadoras;
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
