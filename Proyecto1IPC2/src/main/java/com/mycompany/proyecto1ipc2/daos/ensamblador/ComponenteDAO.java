/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos.ensamblador;

import com.mycompany.proyecto1ipc2.daos.BDCRUD;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Componente;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComponente;
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
public class ComponenteDAO extends BDCRUD<Componente, Integer>{
    private boolean actu = false;
    private boolean desensamblaje = false;
    /**
     * metodo para crear un componente en la base de datos
     *
     * @param entidad datos del componente
     * @throws InvalidDataException si algun dato del componente no es valido
     */
    @Override
    public void insertar(Componente entidad) throws InvalidDataException {
        if (verificarExistencia(entidad)) {
            throw new InvalidDataException("ya existe este tipo de componente registrado con este precio");
        }
        String statement = "insert into Componente(idTipo, cantidad, costo) values(?, ?, ?)";
        try (Connection c = Coneccion.getConeccion(); 
                PreparedStatement st = c.prepareStatement(statement)) {
            st.setInt(1, entidad.getTipo().getId());
            st.setInt(2, entidad.getCantidad());
            st.setDouble(3, entidad.getPrecio());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidDataException("datos no validos" + e);
        }
    }

    /**
     * 
     * @param componente
     * @return
     * @throws InvalidDataException 
     */
    public boolean verificarExistencia(Componente componente) throws InvalidDataException {
        double precio = componente.getPrecio();
        precio = Math.round(precio * 100.0)/100.0;
        String st = "select * from Componente where idTipo = ? and costo = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement st2 = coneccion.prepareStatement(st)) {
            st2.setInt(1, componente.getTipo().getId());
            st2.setDouble(2, precio);
            try (ResultSet result = st2.executeQuery()) {
                if (result.next()) {
                    if (actu && componente.getId() == result.getInt("idComponente")) {
                        return false;
                    }
                    return true;
                }
            }
        } catch(SQLException ex){
            throw new InvalidDataException("datos no validos" + ex);
        }
        return false;
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
        String statement = "select * from TipoComponente inner join Componente on idTipo = idTipoComponente";
        statement += ordenar ? " ORDER BY cantidad " + orden: "";
        try (Connection c = Coneccion.getConeccion(); 
                Statement st = c.createStatement(); 
                ResultSet result = st.executeQuery(statement)) {
            while (result.next()) {
                Componente componente = new Componente();
                componente.setCantidad(result.getInt("cantidad"));
                componente.setId(result.getInt("idComponente"));
                componente.setPrecio(result.getDouble("costo"));
                TipoComponente tipo = new TipoComponente();
                tipo.setNombre(result.getString("nombre"));
                componente.setTipo(tipo);
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
     */
    @Override
    public Optional<Componente> encontrarPorID(Integer id){
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
                    TipoComponente tipo = new TipoComponente();
                    tipo.setId(result.getInt("idTipo"));
                    componente.setPrecio(result.getDouble("costo"));
                    tipo.setNombre(result.getString("nombre"));
                    componente.setTipo(tipo);
                    return Optional.of(componente);
                }
            }
        } catch (SQLException e) {
            System.out.println("error al conectar con la base de datos");
        }
        return Optional.empty();
    }

    @Override
    public void actualizar(Componente entidad) throws InvalidDataException, NotFoundException {
        String statement = "UPDATE Componente SET cantidad = ?, costo = ?, idTipo = ? "
                + "WHERE idComponente = ?";
        actu = true;
        verificarExistencia(entidad);
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement st = coneccion.prepareStatement(statement)){
            st.setInt(1, entidad.getCantidad());
            st.setDouble(2, entidad.getPrecio());
            st.setInt(3, entidad.getTipo().getId());
            st.setInt(4, entidad.getId());
            if (st.executeUpdate() <= 0) {
                throw new NotFoundException("componente con el id: '" + entidad.getId() + "' no encontrado");
            }
        } catch (SQLException e) {
            throw new InvalidDataException("Ingrese correctamente los datos" + e);
        }
    }

    @Override
    public void eliminar(Integer id) throws NotFoundException{
        String statement = "DELETE FROM Componente WHERE idComponente = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement st = coneccion.prepareStatement(statement)){
            st.setInt(1, id);
            if (st.executeUpdate() <= 0) {
                throw new NotFoundException("componente con el id: '" + id + "' no encontrado");
            }
        } catch (SQLException e) {
            throw new NotFoundException("Ingrese correctamente los datos" + e);
        }
    }
    
    public List<Componente> obtenerStockComponentes(Integer idTipo){
        desensamblaje = true;
        return obtenerStock(idTipo);
    }
    
    public List<Componente> obtenerStock(Integer idTipo) {
        List<Componente> componentes = new ArrayList<>();
        String statement = "SELECT * FROM Componente WHERE idTipo = ? ORDER BY(costo) ASC";
        statement += desensamblaje ? " LIMIT 1" : "";
        try (Connection c = Coneccion.getConeccion(); 
                PreparedStatement st = c.prepareStatement(statement)) {
            st.setInt(1, idTipo);
            try (ResultSet result = st.executeQuery()) {
                while (result.next()) {
                    Componente componente = new Componente();
                    componente.setCantidad(result.getInt("cantidad"));
                    componente.setId(result.getInt("idComponente"));
                    componente.setPrecio(result.getDouble("costo"));
                    TipoComponente tipo = new TipoComponente();
                    tipo.setId(idTipo);
                    componente.setTipo(tipo);
                    componentes.add(componente);
                }
            }
        } catch (SQLException e) {
        }
        return componentes;
    }

    public void actualizarEnUno(Componente componente) {
        String query = "UPDATE Componente SET cantidad = cantidad + 1 WHERE idTipo = ? AND costo = ? ";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            statement.setInt(1, componente.getTipo().getId());
            statement.setDouble(2, componente.getPrecio());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
