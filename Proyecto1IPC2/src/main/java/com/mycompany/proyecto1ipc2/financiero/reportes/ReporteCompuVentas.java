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
public class ReporteCompuVentas extends ConsultaDAO{
    
    private TipoComputadora tipo;
    private int cantidad;
    private List<DetalleCompra> detalles;
    private final boolean mayor;

    public ReporteCompuVentas(boolean mayor) {
        this.mayor = mayor;
    }
    
    @Override
    public void realizarConsulta(Consulta consulta) throws InvalidDataException {
        detalles = new ArrayList<>();
        String query = "select c.idTipo, t.nombre ,Count(*) as total from Compra f "
                + "INNER JOIN DetalleCompra d on f.idCompra = d.idCompra RIGHT JOIN Computadora c on c.idComputadora = d.idComputadora "
                + "INNER JOIN TipoComputadora t ON c.idTipo = t.idTipo "
                + "WHERE c.estado = 'VENDIDA' AND f.estado = 1 ";
        query += consulta.tieneFechaInicio() ? "AND fechaCompra >= ? ": "";
        query += consulta.tieneFechaFin() ? "AND fechaCompra <= ? ": "";
        query += "GROUP BY(idTipo) ORDER BY (total) ";
        query += mayor ? "DESC LIMIT 1": "ASC LIMIT 1";
        String query2 = "SELECT f.*, subtotal, precioFabricacion, c.idComputadora FROM Compra f "
                + "INNER JOIN DetalleCompra d ON f.idCompra = d.idCompra  "
                + "INNER JOIN Computadora c on d.idComputadora = c.idComputadora "
                + "WHERE idTipo = ? and f.estado = 1 and c.estado = 'VENDIDA'";
        query2 += consulta.tieneFechaInicio() ? "AND fechaCompra >= ? ": "";
        query2 += consulta.tieneFechaFin() ? "AND fechaCompra <= ? ": "";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            agregarFecha(statement, consulta);
            try(ResultSet result = statement.executeQuery()){
                if (result.next()) {
                    tipo = new TipoComputadora();
                    tipo.setIdTipo(result.getInt("idTipo"));
                    tipo.setNombre(result.getString("nombre"));
                    try(PreparedStatement statement2 = coneccion.prepareStatement(query2)){
                        statement2.setInt(1, tipo.getIdTipo());
                        agregarFechaConCliente(statement2, consulta);
                        try(ResultSet result2 = statement2.executeQuery()){
                            while(result2.next()){
                                DetalleCompra detalle = new DetalleCompra();
                                Compra compra = new Compra();
                                compra.setIdCompra(result2.getInt("idCompra"));
                                compra.setFechaCompra(result2.getDate("fechaCompra").toLocalDate());
                                Usuario usuario = new Usuario();
                                usuario.setNombre(result2.getString("usuario"));
                                compra.setUsuario(usuario);
                                Cliente cliente = new Cliente();
                                cliente.setNit(result2.getInt("nit"));
                                compra.setCliente(cliente);
                                detalle.setCompra(compra);
                                detalle.setSubtotal(result2.getDouble("subtotal"));
                                Computadora computadora = new Computadora();
                                computadora.setIdComputadora(result2.getInt("idComputadora"));
                                computadora.setPrecioFabricacion(result2.getDouble("precioFabricacion"));
                                double ganancia = detalle.getSubtotal() - computadora.getPrecioFabricacion();
                                computadora.setGanancia(Math.round(ganancia * 100.0)/100.0);
                                detalle.setComputadora(computadora);
                                detalles.add(detalle);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException("ingresar valores validos");
        }
    }

    public TipoComputadora getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public List<DetalleCompra> getDetalles() {
        return detalles;
    }
    
}
