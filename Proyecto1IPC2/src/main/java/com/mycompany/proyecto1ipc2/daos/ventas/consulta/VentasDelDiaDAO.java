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
import com.mycompany.proyecto1ipc2.dtos.ventas.DetalleCompra;
import com.mycompany.proyecto1ipc2.enums.EnumEstadoCompu;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.servicios.Coneccion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class VentasDelDiaDAO extends ConsultaDAO<List<Compra>>{

    @Override
    public List<Compra> realizarConsulta(Consulta consulta) throws InvalidDataException {
        List<Compra> compras = new ArrayList<>();
        if (!consulta.tieneFechaInicio()) {
            throw new InvalidDataException("Ingresar una fecha valida");
        }
        String query = "SELECT * FROM Compra c INNER JOIN Cliente l ON c.nit = l.nit WHERE fechaCompra = ? AND estado = 1";
        String query2 = "SELECT d.idComputadora, subtotal, nombre, c.estado FROM DetalleCompra d INNER JOIN Computadora "
                + "c ON d.idComputadora = c.idComputadora INNER JOIN TipoComputadora t ON t.idTipo = c.idTipo"
                + " WHERE idCompra = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            statement.setDate(1, Date.valueOf(consulta.getFechaInicio()));
            try(ResultSet result = statement.executeQuery()){
                while (result.next()) {
                    Compra compra = new Compra();
                    compra.setIdCompra(result.getInt("idCompra"));
                    compra.setFechaCompra(result.getDate("fechaCompra").toLocalDate());
                    Usuario usuario = new Usuario();
                    usuario.setNombre(result.getString("usuario"));
                    compra.setUsuario(usuario);
                    Cliente cliente = new Cliente();
                    cliente.setNit(result.getInt(3));
                    cliente.setDireccion(result.getString("direccion"));
                    cliente.setNombre(result.getString("nombre"));
                    compra.setCliente(cliente);
                    List<DetalleCompra> detalles = new ArrayList<>();
                    double total = 0.00;
                    try(PreparedStatement statement2 = coneccion.prepareStatement(query2)){
                        statement2.setInt(1, compra.getIdCompra());
                        try(ResultSet result2 = statement2.executeQuery()){
                            while (result2.next()) {
                                DetalleCompra detalle = new DetalleCompra();
                                Computadora computadora = new Computadora();
                                computadora.setEstado(EnumEstadoCompu.valueOf(result2.getString("estado")));
                                TipoComputadora tipo = new TipoComputadora();
                                tipo.setNombre(result2.getString("nombre"));
                                computadora.setTipo(tipo);
                                computadora.setIdComputadora(result2.getInt("idComputadora"));
                                detalle.setComputadora(computadora);
                                detalle.setCompra(compra);
                                detalle.setSubtotal(result2.getDouble("subtotal"));
                                detalles.add(detalle);
                                if (computadora.getEstado() == EnumEstadoCompu.VENDIDA) {
                                    total += detalle.getSubtotal();
                                }
                            }
                        }
                    }
                    compra.setTotal((Math.round(total * 100.00)/100.00));
                    compra.setDetalles(detalles);
                    if (!compra.getDetalles().isEmpty()) {
                        compras.add(compra);
                    }
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException("ingresar fecha valida");
        }
        return compras;
    }
    
}
