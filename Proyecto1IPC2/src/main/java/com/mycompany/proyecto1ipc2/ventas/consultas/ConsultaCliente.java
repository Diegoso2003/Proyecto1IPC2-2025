/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ventas.consultas;

import com.mycompany.proyecto1ipc2.Reporte;
import com.mycompany.proyecto1ipc2.daos.ConsultaDAO;
import com.mycompany.proyecto1ipc2.daos.ventas.consulta.ConsultaClienteDAO;
import com.mycompany.proyecto1ipc2.daos.ventas.consulta.ConsultaDevolucionesDAO;
import com.mycompany.proyecto1ipc2.dtos.ventas.Cliente;
import com.mycompany.proyecto1ipc2.dtos.ventas.Compra;
import com.mycompany.proyecto1ipc2.dtos.ventas.Devolucion;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import com.mycompany.proyecto1ipc2.ventas.ClienteCRUD;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class ConsultaCliente{

    private Cliente cliente;
    
    public ConsultaDAO ComprasDeCliente(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        encontrarCliente(request);
        ConsultaClienteDAO reporte1 = new ConsultaClienteDAO(cliente);
        Reporte reporte = new Reporte(reporte1);
        reporte.obtenerDatosConsulta(request);
        return reporte1;
    }
    
    public ConsultaDAO DevolucionesDeCliente(HttpServletRequest request) throws InvalidDataException, NotFoundException{
        encontrarCliente(request);
        ConsultaClienteDAO reporte1 = new ConsultaClienteDAO(cliente);
        Reporte reporte = new Reporte(reporte1);
        reporte.obtenerDatosConsulta(request);
        return reporte1;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void encontrarCliente(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        ClienteCRUD repositorioCliente = new ClienteCRUD();
        cliente = repositorioCliente.obtenerEntidad(request);
    }
    
}
