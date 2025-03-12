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
import com.mycompany.proyecto1ipc2.dtos.financiero.ReporteMasVentas;
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
public class ReporteComprasUsuario extends ConsultaDAO implements Exportacion{
    private ReporteMasVentas reporte;
    private Consulta consulta;
    
    @Override
    public void realizarConsulta(Consulta consulta) throws InvalidDataException {
        this.consulta = consulta;
        reporte = new ReporteMasVentas();
        String query = "SELECT usuario, COUNT(*) AS totalVentas FROM Compra WHERE estado = 1 ";
        query += consulta.tieneFechaInicio() ? " AND fechaCompra >= ? " : "";
        query += consulta.tieneFechaFin() ? " AND fechaCompra <= ? " : "";
        query += "GROUP BY(usuario) ORDER"
                + " BY totalVentas DESC LIMIT 1";
        try (Connection coneccion = Coneccion.getConeccion(); 
                PreparedStatement statement = coneccion.prepareStatement(query)) {
            agregarFecha(statement, consulta);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setNombre(result.getString("usuario"));
                    reporte.setTotalVentas(result.getInt("totalVentas"));
                    reporte.setUsuario(usuario);
                    List<Compra> compras = new ArrayList<>();
                    String query2 = "SELECT * FROM Compra f INNER JOIN Cliente c ON f.nit = c.nit WHERE estado = 1 AND usuario = ? ";
                    query2 += consulta.tieneFechaInicio() ? "AND fechaCompra >= ? " : "";
                    query2 += consulta.tieneFechaFin() ? "AND fechaCompra <= ? " : "";
                    String query3 = "SELECT c.idComputadora, subtotal, nombre, c.estado, precioFabricacion FROM DetalleCompra d "
                            + "INNER JOIN Computadora c ON c.idComputadora = d.idComputadora "
                            + "INNER JOIN TipoComputadora t on t.idTipo = c.idTipo "
                            + "WHERE idCompra = ?";
                    try (PreparedStatement statement2 = coneccion.prepareStatement(query2); 
                            PreparedStatement statement3 = coneccion.prepareStatement(query3)) {
                        statement2.setString(1, usuario.getNombre());
                        agregarFechaConCliente(statement2, consulta);
                        try (ResultSet result2 = statement2.executeQuery()) {
                            while (result2.next()) {
                                Compra compra = new Compra();
                                compra.setIdCompra(result2.getInt("idCompra"));
                                compra.setFechaCompra(result2.getDate("fechaCompra").toLocalDate());
                                compra.setUsuario(usuario);
                                Cliente cliente = new Cliente();
                                cliente.setNit(result2.getInt(3));
                                cliente.setDireccion(result2.getString("direccion"));
                                cliente.setNombre(result2.getString("nombre"));
                                compra.setCliente(cliente);
                                statement3.setInt(1, compra.getIdCompra());
                                try (ResultSet result3 = statement3.executeQuery()) {
                                    List<DetalleCompra> detalles = new ArrayList<>();
                                    double total = 0.0;
                                    while (result3.next()) {
                                        DetalleCompra detalle = new DetalleCompra();
                                        Computadora computadora = new Computadora();
                                        computadora.setIdComputadora(result3.getInt("idComputadora"));
                                        computadora.setPrecioFabricacion(result3.getDouble("precioFabricacion"));
                                        TipoComputadora tipo = new TipoComputadora();
                                        tipo.setNombre(result3.getString("nombre"));
                                        computadora.setTipo(tipo);
                                        computadora.setEstado(EnumEstadoCompu.valueOf(result3.getString("estado")));
                                        detalle.setComputadora(computadora);
                                        detalle.setSubtotal(result3.getDouble("subtotal"));
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
                                if (!compra.getDetalles().isEmpty()) {
                                    compras.add(compra);
                                }
                            }
                        }
                    }
                    reporte.setCompras(compras);
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataException(e.toString());
        }
    }

    public ReporteMasVentas getReporte() {
        return reporte;
    }

    @Override
    public List<String> exportarContenido() {
        List<String> contenido = new ArrayList<>();
        StringBuilder fila = new StringBuilder();
        contenido.add("Reporte de usuario con mas ventas");
        if (consulta.tieneFechaInicio()) {
            fila.append("fecha de Inicio: ").append(consulta.getFechaInicio());
            contenido.add(fila.toString());
            fila = new StringBuilder();
        }
        if (consulta.tieneFechaFin()) {
            fila.append("Fecha fin: ").append(consulta.getFechaFin());
            contenido.add(fila.toString());
        }
        contenido.add("Usuario: "+ reporte.getUsuario().getNombre());
        contenido.add("Total ventas: "+ reporte.getTotalVentas());
        contenido.add("compras:");
        for(Compra compra: reporte.getCompras()){
            contenido.add("Factura NO." + compra.getIdCompra());
            contenido.add("Cliente: " + compra.getCliente().getNombre());
            contenido.add("Nit: " + compra.getCliente().getNit());
            contenido.add("Direccion: " + compra.getCliente().getDireccion());
            contenido.add("Fecha compra: " + compra.getFechaCompra());
            contenido.add("Total: " + compra.getTotal());
            contenido.add("Id computadora,nombre,subtotal,estado,ganancia");
            for(DetalleCompra detalle: compra.getDetalles()){
                fila = new StringBuilder();
                fila.append(detalle.getComputadora().getIdComputadora()).append(",");
                fila.append(detalle.getComputadora().getTipo().getNombre()).append(",");
                fila.append(detalle.getSubtotal()).append(",");
                fila.append(detalle.getComputadora().getEstado().getDescripcion()).append(",");
                fila.append(detalle.getComputadora().getGanancia());
                contenido.add(fila.toString());
            }
        }
        return contenido;
    }


}
