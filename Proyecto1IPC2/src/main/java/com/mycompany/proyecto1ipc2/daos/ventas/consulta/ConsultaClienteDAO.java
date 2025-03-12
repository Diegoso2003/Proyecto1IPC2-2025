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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class ConsultaClienteDAO extends ConsultaDAO {

    private final Cliente cliente;
    private List<Compra> compras;

    public ConsultaClienteDAO(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void realizarConsulta(Consulta consulta) throws InvalidDataException {
        compras = new ArrayList<>();
        String query = "SELECT * FROM Compra WHERE nit = ? AND estado = 1 ";
        query += consulta.tieneFechaInicio() ? "AND fechaCompra >= ? " : "";
        query += consulta.tieneFechaFin() ? "AND fechaCompra <= ? " : "";
        String query2 = "SELECT c.idComputadora, subtotal, nombre, c.estado FROM DetalleCompra d "
                + "INNER JOIN Computadora c ON c.idComputadora = d.idComputadora "
                + "INNER JOIN TipoComputadora t on t.idTipo = c.idTipo "
                + "WHERE idCompra = ?";
        try (Connection coneccion = Coneccion.getConeccion(); 
                PreparedStatement statement = coneccion.prepareStatement(query); 
                PreparedStatement statement2 = coneccion.prepareStatement(query2)) {
            statement.setLong(1, cliente.getNit());
            agregarFechaConCliente(statement, consulta);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Compra compra = new Compra();
                    compra.setIdCompra(result.getInt("idCompra"));
                    compra.setFechaCompra(result.getDate("fechaCompra").toLocalDate());
                    Usuario usuario = new Usuario();
                    usuario.setNombre(result.getString("usuario"));
                    compra.setUsuario(usuario);
                    statement2.setInt(1, compra.getIdCompra());
                    try (ResultSet result2 = statement2.executeQuery()) {
                        List<DetalleCompra> detalles = new ArrayList<>();
                        double total = 0.0;
                        while (result2.next()) {
                            DetalleCompra detalle = new DetalleCompra();
                            Computadora computadora = new Computadora();
                            computadora.setEstado(EnumEstadoCompu.valueOf(result2.getString("estado")));
                            computadora.setIdComputadora(result2.getInt(2));
                            TipoComputadora tipo = new TipoComputadora();
                            tipo.setNombre(result2.getString("nombre"));
                            computadora.setTipo(tipo);
                            detalle.setComputadora(computadora);
                            detalle.setSubtotal(result2.getDouble("subtotal"));
                            detalles.add(detalle);
                            if (computadora.getEstado() == EnumEstadoCompu.VENDIDA) {
                                total += detalle.getSubtotal();
                            }
                        }
                        compra.setDetalles(detalles);
                        compra.setTotal((Math.round(total * 100.0) / 100.0));
                    }
                    if (!compra.getDetalles().isEmpty()) {
                        compras.add(compra);
                    }
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException("Ingresar valores validos");
        }
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
}
