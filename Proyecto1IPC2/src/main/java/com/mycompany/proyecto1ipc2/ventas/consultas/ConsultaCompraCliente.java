/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ventas.consultas;

import com.mycompany.proyecto1ipc2.Reporte;
import com.mycompany.proyecto1ipc2.daos.ventas.consulta.ConsultaClienteDAO;
import com.mycompany.proyecto1ipc2.dtos.ventas.Cliente;
import com.mycompany.proyecto1ipc2.dtos.ventas.Compra;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import com.mycompany.proyecto1ipc2.ventas.ClienteCRUD;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class ConsultaCompraCliente{

    private Cliente cliente;
    private List<Compra> consulta;
    
    public ConsultaCompraCliente ComprasDeCliente(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        ClienteCRUD repositorioCliente = new ClienteCRUD();
        cliente = repositorioCliente.obtenerEntidad(request);
        Reporte<List<Compra>> reporte = new Reporte<>(new ConsultaClienteDAO(cliente));
        consulta = reporte.obtenerDatosConsulta(request);
        return this;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Compra> getConsulta() {
        return consulta;
    }
    
}
