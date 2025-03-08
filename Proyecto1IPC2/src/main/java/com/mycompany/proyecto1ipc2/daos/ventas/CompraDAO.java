/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos.ventas;

import com.mycompany.proyecto1ipc2.daos.BDCRUD;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora;
import com.mycompany.proyecto1ipc2.dtos.ventas.Cliente;
import com.mycompany.proyecto1ipc2.dtos.ventas.Compra;
import com.mycompany.proyecto1ipc2.dtos.ventas.DetalleCompra;
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
public class CompraDAO extends BDCRUD<Compra, Integer>{

    @Override
    public void insertar(Compra entidad) throws InvalidDataException {
        String query = "INSERT INTO Compra(fechaCompra, nit, usuario) VALUES(?, ?, ?)";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setDate(1, Date.valueOf(entidad.getFechaCompra()));
            statement.setInt(2, entidad.getCliente().getNit());
            statement.setString(3, entidad.getUsuario().getNombre());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entidad.setIdCompra(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException("Ingrese correctamente los datos solicitados");
        }
    }

    @Override
    public Optional<Compra> encontrarPorID(Integer id) throws InvalidDataException {
        String query = "SELECT * FROM Compra c INNER JOIN Cliente l ON c.nit = l.nit WHERE idCompra = ?";
        String query2 = "SELECT d.idComputadora, subtotal, nombre FROM DetalleCompra d INNER JOIN Computadora "
                + "c ON d.idComputadora = c.idComputadora INNER JOIN TipoComputadora t ON t.idTipo = c.idTipo"
                + " WHERE idCompra = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query);
                PreparedStatement statement2 = coneccion.prepareStatement(query2)){
            statement.setInt(1, id);
            statement2.setInt(1, id);
            try(ResultSet result = statement.executeQuery();
                    ResultSet result2 = statement2.executeQuery()){
                if (result.next()) {
                    Compra compra = new Compra();
                    compra.setIdCompra(result.getInt("idCompra"));
                    compra.setFechaCompra(result.getDate("fechaCompra").toLocalDate());
                    Cliente cliente = new Cliente();
                    cliente.setNit(result.getInt(3));
                    cliente.setDireccion(result.getString("direccion"));
                    cliente.setNombre(result.getString("nombre"));
                    compra.setCliente(cliente);
                    List<DetalleCompra> detalles = new ArrayList<>();
                    double total = 0.00;
                    while(result2.next()){
                        DetalleCompra detalle = new DetalleCompra();
                        Computadora computadora = new Computadora();
                        TipoComputadora tipo = new TipoComputadora();
                        tipo.setNombre(result2.getString("nombre"));
                        computadora.setTipo(tipo);
                        computadora.setIdComputadora(result2.getInt("idComputadora"));
                        detalle.setComputadora(computadora);
                        detalle.setCompra(compra);
                        detalle.setSubtotal(result2.getDouble("subtotal"));
                        detalles.add(detalle);
                        total += detalle.getSubtotal();
                    }
                    compra.setTotal((Math.round(total * 100.00)/100.00));
                    compra.setDetalles(detalles);
                    return Optional.of(compra);
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException(e.toString());
        }
        return Optional.empty();
    }

    @Override
    public List<Compra> obtenerTodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Compra entidad) throws InvalidDataException, NotFoundException {
        String query = "UPDATE Compra set estado = 1 WHERE idCompra = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            statement.setInt(1, entidad.getIdCompra());
            if (statement.executeUpdate() <= 0) {
                throw new NotFoundException("no se encontro la factura");
            }
        } catch (SQLException e) {
            throw new InvalidDataException("id ingresado invalido");
        }
    }

    @Override
    public void eliminar(Integer id) throws NotFoundException {
        String query = "DELETE FROM Compra WHERE idCompra = ? AND estado = 0";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            statement.setInt(1, id);
            if (statement.executeUpdate() <= 0) {
                throw new NotFoundException("no se encontro una factura con este id");
            }
        } catch (SQLException e) {
            throw new NotFoundException("se ingreso un id invalido");
        }
    }

}
