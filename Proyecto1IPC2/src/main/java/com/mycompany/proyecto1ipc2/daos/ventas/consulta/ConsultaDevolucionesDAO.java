/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos.ventas.consulta;

import com.mycompany.proyecto1ipc2.Consulta;
import com.mycompany.proyecto1ipc2.daos.ConsultaDAO;
import com.mycompany.proyecto1ipc2.dtos.Usuario;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora;
import com.mycompany.proyecto1ipc2.dtos.ventas.Cliente;
import com.mycompany.proyecto1ipc2.dtos.ventas.Compra;
import com.mycompany.proyecto1ipc2.dtos.ventas.Devolucion;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.servicios.Coneccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class ConsultaDevolucionesDAO extends ConsultaDAO{

    private final Cliente cliente;
    private List<Devolucion> devoluciones;

    public ConsultaDevolucionesDAO(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public void realizarConsulta(Consulta consulta) throws InvalidDataException {
        devoluciones = new ArrayList<>();
        String query = "SELECT fechaDevolucion, d.idCompra, d.costoVenta, nombre, idDevolucion, usuario, "
                + "ROUND((precioFabricacion / 3),2 ) as perdida, fechaCompra, c.idComputadora FROM Devolucion d "
                + "INNER JOIN Compra f ON d.idCompra = f.idCompra "
                + "INNER JOIN Computadora c ON c.idComputadora = d.idComputadora "
                + "INNER JOIN TipoComputadora t ON t.idTipo = c.idTipo WHERE nit = ? AND f.estado = 1";
        query += consulta.tieneFechaInicio() ? "AND fechaDevolucion >= ? ": "";
        query += consulta.tieneFechaFin() ? "AND fechaDevolucion <= ? ": "";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            statement.setInt(1, cliente.getNit());
            agregarFechaConCliente(statement, consulta);
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    Devolucion devolucion = new Devolucion();
                    devolucion.setIdDevolucion(result.getInt("idDevolucion"));
                    devolucion.setFechaDevolucion(result.getDate("fechaDevolucion").toLocalDate());
                    Compra compra = new Compra();
                    TipoComputadora tipo = new TipoComputadora();
                    compra.setFechaCompra(result.getDate("fechaCompra").toLocalDate());
                    tipo.setNombre(result.getString("nombre"));
                    Computadora computadora = new Computadora();
                    computadora.setIdComputadora(result.getInt("idComputadora"));
                    computadora.setTipo(tipo);
                    devolucion.setComputadora(computadora);
                    compra.setIdCompra(result.getInt("idCompra"));
                    devolucion.setCompra(compra);
                    devolucion.setCostoVenta(result.getDouble("costoVenta"));
                    devolucion.setPerdida(result.getDouble("perdida"));
                    Usuario usuario = new Usuario();
                    usuario.setNombre(result.getString("usuario"));
                    compra.setUsuario(usuario);
                    devoluciones.add(devolucion);
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException("ingresar valores validos");
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Devolucion> getDevoluciones() {
        return devoluciones;
    }
    
}
