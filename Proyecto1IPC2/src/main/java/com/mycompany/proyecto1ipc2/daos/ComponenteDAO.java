/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos;

import com.mycompany.proyecto1ipc2.dtos.Componente;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
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
public class ComponenteDAO implements BDCRUD<Componente, Integer>{


    /**
     * metodo para crear un componente en la base de datos
     *
     * @param entidad datos del componente
     * @throws InvalidDataException si algun dato del componente no es valido
     */
    @Override
    public void insertar(Componente entidad) throws InvalidDataException {
        String statement = "insert into Componente(idTipo, cantidad, costo) values(?, ?, ?)";
        try (Connection c = Coneccion.getConeccion(); 
                PreparedStatement st = c.prepareStatement(statement)) {
            verificarExistencia(entidad.getIdTipo(), entidad.getPrecio());
            st.setInt(1, entidad.getIdTipo());
            st.setInt(2, entidad.getCantidad());
            st.setDouble(3, entidad.getPrecio());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidDataException("datos no validos");
        }
    }

    /**
     * verifica que no exista un componente con el mismo nombre y precio ya
     * guardado en la base de datos
     *
     * @param codigo id del tipo de componente
     * @param precio precio del componente
     * @throws SQLException
     * @throws InvalidDataException
     */
    private void verificarExistencia(int codigo, double precio) throws SQLException, InvalidDataException {
        precio = Math.round(precio * 100.0)/100.0;
        String st = "select * from Componente where idTipo = ? and costo = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement st2 = coneccion.prepareStatement(st)) {
            st2.setInt(1, codigo);
            st2.setDouble(2, precio);
            try (ResultSet result = st2.executeQuery()) {
                if (result.next()) {
                    throw new InvalidDataException("el componente con este nombre y precio ya existe!!!");
                }
            }
        }
    }

    /**
     * metodo para listar todos los componentes existentes en la base de datos
     * junto con todos sus datos
     *
     * @return
     */
    @Override
    public List<Componente> obtenerTodo() {
        List<Componente> componentes = new ArrayList<>();
        String stament = "select * from TipoComponente inner join Componente on idTipo = idTipoComponente";
        try (Connection c = Coneccion.getConeccion(); 
                Statement st = c.createStatement(); 
                ResultSet result = st.executeQuery(stament)) {
            while (result.next()) {
                Componente componente = new Componente();
                componente.setCantidad(result.getInt("cantidad"));
                componente.setId(result.getInt("idComponente"));
                componente.setPrecio(result.getDouble("costo"));
                componente.setNombre(result.getString("nombre"));
                componentes.add(componente);
            }
        } catch (SQLException e) {
        }
        return componentes;
    }

    /**
     * metodo para conseguir los datos del componente en base el id enviado
     * @param id el id del componente
     * @return un optional que puede contener al componente
     * @throws InvalidDataException en caso de que el id enviado no coincidiera 
     * con ningun componente en la base de datos
     */
    @Override
    public Optional<Componente> encontrarPorID(Integer id) throws InvalidDataException {
        String statement = "select * from TipoComponente inner join Componente on idTipo = idTipoComponente"
                + " where idComponente = ?";
        try (Connection c = Coneccion.getConeccion(); 
                PreparedStatement st = c.prepareStatement(statement)) {
            st.setInt(1, id);
            try (ResultSet result = st.executeQuery()) {
                if (result.next()) {
                    Componente componente = new Componente();
                    componente.setCantidad(result.getInt("cantidad"));
                    componente.setId(result.getInt("idComponente"));
                    componente.setPrecio(result.getDouble("costo"));
                    componente.setNombre(result.getString("nombre"));
                    return Optional.of(componente);
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException("componente con id: '" + id + "' no encontrado");
        }
        return Optional.empty();
    }

    @Override
    public void actualizar(Componente entidad) throws InvalidDataException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Integer id) throws InvalidDataException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
