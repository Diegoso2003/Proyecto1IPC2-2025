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
public class ReporteDevolucion extends ConsultaDAO implements Exportacion{

    private List<Devolucion> devoluciones;
    private Consulta consulta;
    
    @Override
    public void realizarConsulta(Consulta consulta) throws InvalidDataException {
        this.consulta = consulta;
        devoluciones = new ArrayList<>();
        String query = "SELECT fechaDevolucion, d.idCompra, d.costoVenta, t.nombre as nombreTipo, l.nombre as cliente, f.nit, fechaCompra,"
                + "ROUND((precioFabricacion / 3),2 ) as perdida, c.idComputadora, idDevolucion, usuario FROM Devolucion d "
                + "INNER JOIN Compra f ON d.idCompra = f.idCompra "
                + "INNER JOIN Cliente l ON f.nit = l.nit "
                + "INNER JOIN Computadora c ON c.idComputadora = d.idComputadora "
                + "INNER JOIN TipoComputadora t ON t.idTipo = c.idTipo WHERE f.estado = 1 ";
        query += consulta.tieneFechaInicio() ? "AND fechaDevolucion >= ? ": "";
        query += consulta.tieneFechaFin() ? "AND fechaDevolucion <= ? ": "";
        try (Connection coneccion = Coneccion.getConeccion();
                PreparedStatement statement = coneccion.prepareStatement(query)){
            agregarFecha(statement, consulta);
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    Devolucion devolucion = new Devolucion();
                    devolucion.setIdDevolucion(result.getInt("idDevolucion"));
                    devolucion.setFechaDevolucion(result.getDate("fechaDevolucion").toLocalDate());
                    Compra compra = new Compra();
                    TipoComputadora tipo = new TipoComputadora();
                    tipo.setNombre(result.getString("nombreTipo"));
                    Computadora computadora = new Computadora();
                    computadora.setIdComputadora(result.getInt("idComputadora"));
                    computadora.setTipo(tipo);
                    devolucion.setComputadora(computadora);
                    compra.setIdCompra(result.getInt("idCompra"));
                    devolucion.setCompra(compra);
                    devolucion.setCostoVenta(result.getDouble("costoVenta"));
                    devolucion.setPerdida(result.getDouble("perdida"));
                    Cliente cliente = new Cliente();
                    cliente.setNombre(result.getString("cliente"));
                    cliente.setNit(result.getInt("nit"));
                    compra.setFechaCompra(result.getDate("fechaCompra").toLocalDate());
                    compra.setCliente(cliente);
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

    public List<Devolucion> getDevoluciones() {
        return devoluciones;
    }

    @Override
    public List<String> exportarContenido() {
        List<String> contenido = new ArrayList<>();
        StringBuilder fila = new StringBuilder();
        contenido.add("Reporte de devoluciones");
        if (consulta.tieneFechaInicio()) {
            fila.append("fecha de Inicio: ").append(consulta.getFechaInicio());
            contenido.add(fila.toString());
            fila = new StringBuilder();
        }
        if (consulta.tieneFechaFin()) {
            fila.append("Fecha fin: ").append(consulta.getFechaFin());
            contenido.add(fila.toString());
            fila = new StringBuilder();
        }
        fila.append("Cliente,NIT,Fecha Compra,Precio Compra,Factura NO.,Vendedor,ID Comp.,Tipo Comp.,Fecha Devol.,Perdida");
        contenido.add(fila.toString());
        for(Devolucion devolucion: devoluciones){
            fila = new StringBuilder();
            fila.append(devolucion.getCompra().getCliente().getNombre()).append(",");
            fila.append(devolucion.getCompra().getCliente().getNit()).append(",");
            fila.append(devolucion.getCompra().getFechaCompra()).append(",");
            fila.append(devolucion.getCostoVenta()).append(",");
            fila.append(devolucion.getCompra().getIdCompra()).append(",");
            fila.append(devolucion.getCompra().getUsuario().getNombre()).append(",");
            fila.append(devolucion.getComputadora().getIdComputadora()).append(",");
            fila.append(devolucion.getComputadora().getTipo().getNombre()).append(",");
            fila.append(devolucion.getFechaDevolucion()).append(",");
            fila.append(devolucion.getPerdida());
            contenido.add(fila.toString());
        }
        return contenido;
    }
    
}
