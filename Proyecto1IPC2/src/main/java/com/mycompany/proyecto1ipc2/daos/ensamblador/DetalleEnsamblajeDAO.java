/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos.ensamblador;

import com.mycompany.proyecto1ipc2.daos.BDCRUD;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.DetalleEnsamblaje;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import com.mycompany.proyecto1ipc2.servicios.Coneccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class DetalleEnsamblajeDAO extends BDCRUD<DetalleEnsamblaje, Integer>{

    @Override
    public void insertar(DetalleEnsamblaje entidad) throws InvalidDataException {
        String query = "INSERT INTO DetalleEnsamblaje(idComputadora, idTipoComponente, cantidad) VALUES(?, ?, ?)";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            statement.setInt(1, entidad.getComputadora().getIdComputadora());
            statement.setInt(2, entidad.getTipoComponente().getId());
            statement.setInt(3, entidad.getCantidad());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidDataException("ingresar valores validos" + e);
        }
    }

    @Override
    public Optional<DetalleEnsamblaje> encontrarPorID(Integer id) throws InvalidDataException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetalleEnsamblaje> obtenerTodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(DetalleEnsamblaje entidad) throws InvalidDataException, NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Integer id) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
