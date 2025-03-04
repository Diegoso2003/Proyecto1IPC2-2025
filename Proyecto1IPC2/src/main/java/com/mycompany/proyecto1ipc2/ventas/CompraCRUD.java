/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ventas;

import com.mycompany.proyecto1ipc2.CRUD;
import com.mycompany.proyecto1ipc2.daos.ventas.ClienteDAO;
import com.mycompany.proyecto1ipc2.daos.ventas.CompraDAO;
import com.mycompany.proyecto1ipc2.dtos.Usuario;
import com.mycompany.proyecto1ipc2.dtos.ventas.Cliente;
import com.mycompany.proyecto1ipc2.dtos.ventas.Compra;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class CompraCRUD extends CRUD<Compra>{

    public CompraCRUD() {
        repositorio = new CompraDAO();
        nombre = "factura";
    }

    @Override
    protected void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        try {
            entidad = new Compra();
            Usuario usuario = new Usuario();
            usuario.setNombre((String)request.getSession().getAttribute("Usuario"));
            ClienteDAO repositorioCliente = new ClienteDAO();
            if (request.getParameter("nit").length() != 9) {
                throw new InvalidDataException("el nit ingresado no es valido");
            }
            int id = Integer.parseInt(request.getParameter("nit"));
            Optional<Cliente> posibleCliente = repositorioCliente.encontrarPorID(id);
            Cliente cliente = posibleCliente.orElseThrow(() -> new NotFoundException("cliente con nit: '" + id + "' no encontrado"));
            entidad.setCliente(cliente);
            entidad.setUsuario(usuario);
            entidad.setFechaCompra(LocalDate.parse(request.getParameter("fecha")));
        } catch (NumberFormatException | DateTimeParseException | NullPointerException e) {
            throw new InvalidDataException("Ingrese correctamente los datos solicitados");
        }
    }
    
}
