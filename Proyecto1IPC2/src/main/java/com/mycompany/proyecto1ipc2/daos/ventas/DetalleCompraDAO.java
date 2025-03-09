/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos.ventas;

import com.mycompany.proyecto1ipc2.daos.BDCRUD;
import com.mycompany.proyecto1ipc2.dtos.ventas.DetalleCompra;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import com.mycompany.proyecto1ipc2.servicios.Coneccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class DetalleCompraDAO extends BDCRUD<DetalleCompra, DetalleCompra>{

    @Override
    public void insertar(DetalleCompra entidad) throws InvalidDataException {
        String query = "INSERT INTO DetalleCompra (idCompra, idComputadora, subtotal)"
                + " VALUES (?, ?, ?)";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            statement.setInt(1, entidad.getCompra().getIdCompra());
            statement.setInt(2, entidad.getComputadora().getIdComputadora());
            statement.setDouble(3, entidad.getComputadora().getTipo().getPrecio());
            statement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new InvalidDataException("la computadora con el codigo ingresado ya ha sido agregado");
            }
            throw new InvalidDataException(e.toString());
        }
    }

    @Override
    public Optional<DetalleCompra> encontrarPorID(DetalleCompra id) throws InvalidDataException {
        String query = "SELECT subtotal FROM DetalleCompra WHERE idCompra = ? AND idComputadora = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            statement.setInt(1, id.getCompra().getIdCompra());
            statement.setInt(2, id.getComputadora().getIdComputadora());
            try(ResultSet result = statement.executeQuery()){
                if (result.next()) {
                    DetalleCompra detalle = new DetalleCompra();
                    detalle.setSubtotal(result.getDouble("subtotal"));
                    return Optional.of(detalle);
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException("ingresar valores validos");
        }
        return Optional.empty();
    }

    @Override
    public List<DetalleCompra> obtenerTodo() {
        List<DetalleCompra> detalles = new ArrayList<>();
        return detalles;
    }

    @Override
    public void actualizar(DetalleCompra entidad) throws InvalidDataException, NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(DetalleCompra id) throws NotFoundException {
        String query = "DELETE FROM DetalleCompra WHERE idComputadora = ? AND idCompra = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            statement.setInt(1, id.getComputadora().getIdComputadora());
            statement.setInt(2, id.getCompra().getIdCompra());
            if (statement.executeUpdate() <= 0) {
                throw new NotFoundException("No se encontro el elemento ingresado");
            }
        } catch (Exception e) {
        }
    }
    
}
