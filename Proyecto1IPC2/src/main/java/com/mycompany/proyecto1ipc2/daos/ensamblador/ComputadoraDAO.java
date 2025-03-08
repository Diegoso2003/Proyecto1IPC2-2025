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
        String query = "SELECT * FROM Computadora c INNER JOIN TipoComputadora t ON c.idTipo = t.idTipo WHERE"
                + " idComputadora = ?";
        try (Connection c = Coneccion.getConeccion(); 
                PreparedStatement st = c.prepareStatement(query)) {
            st.setInt(1, id);
            try(ResultSet result = st.executeQuery()){
                if(result.next()) {
                    Computadora computadora = new Computadora();
                    computadora.setIdComputadora(result.getInt("idComputadora"));
                    computadora.setFechaEnsamblaje(result.getDate("fechaEnsamblaje").toLocalDate());
                    computadora.setEnsamblador(result.getString("ensamblador"));
                    computadora.setPrecioFabricacion(result.getDouble("precioFabricacion"));
                    TipoComputadora tipo = new TipoComputadora();
                    tipo.setIdTipo(6);
                    tipo.setNombre(result.getString("nombre"));
                    tipo.setPrecio(result.getDouble("costoVenta"));
                    computadora.setTipo(tipo);
                    computadora.setEstado(EnumEstadoCompu.valueOf(result.getString("estado")));
                    return Optional.of(computadora);
                }
            }
        } catch (SQLException e) {
        }
        return Optional.empty();
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
                computadora.setIdComputadora(result.getInt("idComputadora"));
                computadora.setFechaEnsamblaje(result.getDate("fechaEnsamblaje").toLocalDate());
                computadora.setEnsamblador(result.getString("ensamblador"));
                computadora.setPrecioFabricacion(result.getDouble("precioFabricacion"));
                TipoComputadora tipo = new TipoComputadora();
                tipo.setIdTipo(6);
                tipo.setNombre(result.getString("nombre"));
                computadora.setTipo(tipo);
                computadora.setEstado(EnumEstadoCompu.valueOf(result.getString("estado")));
                computadoras.add(computadora);
            }
        } catch (SQLException e) {
        }
        return computadoras;
    }

    /**
     * solamente actualiza el estado de la computadora a 'ENSAMBLADA', 'VENDIDA'
     * 'DEVUELTA'
     * @param entidad
     * @throws InvalidDataException
     * @throws NotFoundException 
     */
    @Override
    public void actualizar(Computadora entidad) throws InvalidDataException, NotFoundException {
        String query = "UPDATE Computadora SET estado = ? WHERE idComputadora = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            statement.setString(1, entidad.getEstado().toString());
            statement.setInt(2, entidad.getIdComputadora());
            if (statement.executeUpdate() <= 0) {
                throw new NotFoundException("no se encontro la computadora con id: '"
                + entidad.getIdComputadora() + "'");
            }
        } catch (SQLException e) {
            throw new InvalidDataException("datos ingresados invalidos");
        }
    }

    @Override
    public void eliminar(Integer id) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<Computadora> obtenerComputadorasDisponibles() {
        List<Computadora> computadoras = new ArrayList<>();
        String statement = "SELECT * FROM Computadora c INNER JOIN TipoComputadora t ON c.idTipo = t.idTipo"
                + " WHERE estado = 'ENSAMBLADA'";
        try (Connection c = Coneccion.getConeccion(); 
                Statement st = c.createStatement(); 
                ResultSet result = st.executeQuery(statement)) {
            while (result.next()) {
                Computadora computadora = new Computadora();
                computadora.setIdComputadora(result.getInt("idComputadora"));
                computadora.setFechaEnsamblaje(result.getDate("fechaEnsamblaje").toLocalDate());
                computadora.setEnsamblador(result.getString("ensamblador"));
                computadora.setPrecioFabricacion(result.getDouble("precioFabricacion"));
                TipoComputadora tipo = new TipoComputadora();
                tipo.setIdTipo(6);
                tipo.setNombre(result.getString("nombre"));
                tipo.setPrecio(result.getDouble("costoVenta"));
                computadora.setTipo(tipo);
                computadora.setEstado(EnumEstadoCompu.valueOf(result.getString("estado")));
                computadoras.add(computadora);
            }
        } catch (SQLException e) {
        }
        return computadoras;
    }
    
}
