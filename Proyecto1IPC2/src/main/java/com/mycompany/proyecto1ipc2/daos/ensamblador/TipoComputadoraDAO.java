/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos.ensamblador;

import com.mycompany.proyecto1ipc2.daos.BDCRUD;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComponente;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora;
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
public class TipoComputadoraDAO extends BDCRUD<TipoComputadora, Integer>{

    @Override
    public void insertar(TipoComputadora entidad) throws InvalidDataException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<TipoComputadora> encontrarPorID(Integer id) throws InvalidDataException {
        String statement = "SELECT * FROM TipoComputadora WHERE idTipo = ?";
        String statement2 = "SELECT nombre, cantidad, i.idTipoComponente AS componente FROM TipoComponente t INNER JOIN "
                + "Indicacion i ON i.idTipoComponente = t.idTipoComponente WHERE idTipoComputadora = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement st = coneccion.prepareStatement(statement);
                PreparedStatement st2 = coneccion.prepareStatement(statement2)){
            st.setInt(1, id);
            st2.setInt(1, id);
            try(ResultSet result1 = st.executeQuery();
                    ResultSet result2 = st2.executeQuery()){
                if (result1.next()) {
                    TipoComputadora computadora = new TipoComputadora();
                    computadora.setIdTipo(result1.getInt("idTipo"));
                    computadora.setNombre(result1.getString("nombre"));
                    computadora.setPrecio(result1.getDouble("costoVenta"));
                    List<TipoComponente> indicaciones = new ArrayList<>();
                    while(result2.next()){
                        TipoComponente componente = new TipoComponente();
                        componente.setCantidad(result2.getInt("cantidad"));
                        componente.setNombre(result2.getString("nombre"));
                        componente.setId(result2.getInt("componente"));
                        indicaciones.add(componente);
                    }
                    computadora.setIndicaciones(indicaciones);
                    return Optional.of(computadora);
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException(e.toString());
        }
        return Optional.empty();
    }

    @Override
    public List<TipoComputadora> obtenerTodo() {
        List<TipoComputadora> computadoras = new ArrayList<>();
        String statement = "SELECT * FROM TipoComputadora";
        try (Connection coneccion = Coneccion.getConeccion();
                Statement st = coneccion.createStatement();
                ResultSet result = st.executeQuery(statement)){
            while(result.next()){
                TipoComputadora computadora = new TipoComputadora();
                computadora.setIdTipo(result.getInt("idTipo"));
                computadora.setNombre(result.getString("nombre"));
                computadora.setPrecio(result.getDouble("costoVenta"));
                computadoras.add(computadora);
            }
        } catch (SQLException e) {
            System.out.println("fallo al conectar con la db: "+ e);
        }
        return computadoras;
    }

    @Override
    public void actualizar(TipoComputadora entidad) throws InvalidDataException, NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Integer id) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
