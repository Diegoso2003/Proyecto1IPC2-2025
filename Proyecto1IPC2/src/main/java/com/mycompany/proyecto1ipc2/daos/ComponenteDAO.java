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

/**
 *
 * @author rafael-cayax
 */
public class ComponenteDAO {
    
    private Coneccion coneccion;

    public void CrearComponente(Componente componente) throws InvalidDataException {
        coneccion = new Coneccion();
        String statement = "insert into Componente(idTipo, cantidad, costo) values(?, ?, ?)";
        try (Connection c = coneccion.getConeccion(); 
                PreparedStatement st = c.prepareStatement(statement)) {
            TipoComponenteDAO tipo = new TipoComponenteDAO();
            int codigo = tipo.crearTipoComponente(coneccion, componente.getNombre());
            verificarExistencia(codigo, componente.getPrecio());
            st.setInt(1, codigo);
            st.setInt(2, componente.getCantidad());
            st.setDouble(3, componente.getPrecio());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidDataException("datos no validos");
        }
    }

    private void verificarExistencia(int codigo, double precio) throws SQLException, InvalidDataException {
        String st = "select * from Componente where idTipo = ? and costo = ?";
        try(PreparedStatement st2 = coneccion.getConeccion().prepareStatement(st)){
            st2.setInt(1, codigo);
            st2.setDouble(2, precio);
            try(ResultSet result = st2.executeQuery()){
                if (result.next()) {
                    throw new InvalidDataException("el componente con este nombre y precio ya existe!!!");
                }
            }
        }
            
    }

}
