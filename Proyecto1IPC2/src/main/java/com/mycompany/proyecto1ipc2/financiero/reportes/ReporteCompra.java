/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.financiero.reportes;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class ReporteCompra extends ConsultaDAO{

    private List<Compra> compras;
    @Override
    public void realizarConsulta(Consulta consulta) throws InvalidDataException {
        compras = new ArrayList<>();
        String query = "SELECT * FROM Compra f INNER JOIN Cliente c ON f.nit = c.nit WHERE estado = 1 ";
        query += consulta.tieneFechaInicio() ? "AND fechaCompra >= ? ": "";
        query += consulta.tieneFechaFin() ? "AND fechaCompra <= ? ": "";
        String query2 = "SELECT c.idComputadora, subtotal, nombre, c.estado, precioFabricacion FROM DetalleCompra d "
                + "INNER JOIN Computadora c ON c.idComputadora = d.idComputadora "
                + "INNER JOIN TipoComputadora t on t.idTipo = c.idTipo "
                + "WHERE idCompra = ?";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query);
                PreparedStatement statement2 = coneccion.prepareStatement(query2)){
            agregarFecha(statement, consulta);
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
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
                    statement2.setInt(1, compra.getIdCompra());
                    try(ResultSet result2 = statement2.executeQuery()){
                        List<DetalleCompra> detalles = new ArrayList<>();
                        double total = 0.0;
                        while(result2.next()){
                            DetalleCompra detalle = new DetalleCompra();
                            Computadora computadora = new Computadora();
                            computadora.setEstado(EnumEstadoCompu.valueOf(result2.getString("estado")));
                            computadora.setIdComputadora(result2.getInt("idComputadora"));
                            computadora.setPrecioFabricacion(result2.getDouble("precioFabricacion"));
                            TipoComputadora tipo = new TipoComputadora();
                            tipo.setNombre(result2.getString("nombre"));
                            computadora.setTipo(tipo);
                            detalle.setComputadora(computadora);
                            detalle.setSubtotal(result2.getDouble("subtotal"));
                            if (computadora.getEstado() == EnumEstadoCompu.VENDIDA) {
                                total += detalle.getSubtotal();
                            }
                            computadora.setGanancia(detalle.getSubtotal() - computadora.getPrecioFabricacion());
                            computadora.setGanancia(Math.round(computadora.getGanancia() * 100.0)/100.0);
                            detalles.add(detalle);
                        }
                        compra.setDetalles(detalles);
                        compra.setTotal((Math.round(total * 100.0) / 100.0));
                    }
                    compras.add(compra);
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException("Ingresar valores validos");
        }
    }

    public List<Compra> getCompras() {
        return compras;
    }
    
}
