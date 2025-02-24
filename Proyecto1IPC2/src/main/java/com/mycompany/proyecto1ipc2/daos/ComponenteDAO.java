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

/**
 *
 * @author rafael-cayax
 */
public class ComponenteDAO {
    
    private Coneccion coneccion;

    /**
     * metodo para crear un componente en la base de datos
     * @param componente datos del componente
     * @throws InvalidDataException si algun dato del componente no es valido
     */
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

    /**
     * verifica que no exista un componente con el mismo nombre y precio ya 
     * guardado en la base de datos
     * @param codigo id del tipo de componente
     * @param precio precio del componente
     * @throws SQLException
     * @throws InvalidDataException 
     */
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
    
    /**
     * metodo para listar todos los componentes existentes en la base de datos
     * junto con todos sus datos
     * @return 
     */
    public List<Componente> listarComponentes(){
        List<Componente> componentes = new ArrayList<>();
        String stament = "select * from TipoComponente inner join Componente on idTipo = idTipoComponente";
        coneccion = new Coneccion();
        try (Connection c = coneccion.getConeccion();
                Statement st = c.createStatement();
                ResultSet result = st.executeQuery(stament)){
            while(result.next()){
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

}
