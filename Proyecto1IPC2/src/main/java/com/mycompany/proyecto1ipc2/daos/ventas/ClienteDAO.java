/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos.ventas;

import com.mycompany.proyecto1ipc2.daos.BDCRUD;
import com.mycompany.proyecto1ipc2.dtos.ventas.Cliente;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import com.mycompany.proyecto1ipc2.servicios.Coneccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class ClienteDAO extends BDCRUD<Cliente, Integer>{

    @Override
    public void insertar(Cliente entidad) throws InvalidDataException {
        String query = "INSERT INTO Cliente(nit, nombre, direccion) VALUES(?, ?, ?)";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            statement.setInt(1, entidad.getNit());
            statement.setString(2, entidad.getNombre());
            statement.setString(3, entidad.getDireccion());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidDataException("Ingrese valores validos");
        }
    }

    @Override
    public Optional<Cliente> encontrarPorID(Integer id) throws InvalidDataException {
        String query = "SELECT * FROM Cliente WHERE nit = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            statement.setInt(1, id);
            try(ResultSet result = statement.executeQuery()){
                if (result.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setNit(id);
                    cliente.setNombre(result.getString("nombre"));
                    cliente.setDireccion(result.getString("direccion"));
                    return Optional.of(cliente);
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException("ingresar valores validos");
        }
        return Optional.empty();
    }

    @Override
    public List<Cliente> obtenerTodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Cliente entidad) throws InvalidDataException, NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Integer id) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
