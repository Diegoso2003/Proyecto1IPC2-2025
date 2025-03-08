/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ventas;

import com.mycompany.proyecto1ipc2.CRUD;
import com.mycompany.proyecto1ipc2.daos.ventas.DetalleCompraDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora;
import com.mycompany.proyecto1ipc2.dtos.ventas.Compra;
import com.mycompany.proyecto1ipc2.dtos.ventas.DetalleCompra;
import com.mycompany.proyecto1ipc2.ensamblaje.ComputadoraCRUD;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author rafael-cayax
 */
public class DetalleCompraCRUD extends CRUD<DetalleCompra>{
    private Compra compra;

    public DetalleCompraCRUD() {
        super("Detalle de la factura", new DetalleCompraDAO());
    }
    
    @Override
    public void crearEntidad(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        super.crearEntidad(request);
        compra.getDetalles().add(entidad);
        double total = compra.getTotal() + entidad.getSubtotal();
        compra.setTotal(Math.round(total * 100.00) / 100.00);
    }
    
    @Override
    protected void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        ComputadoraCRUD computadora2 = new ComputadoraCRUD();
        computadora2.setIdNombre("idComputadora");
        Computadora computadora = computadora2.obtenerEntidad(request);
        validarComputadora(computadora);
        entidad = new DetalleCompra();
        entidad.setCompra(compra);
        entidad.setComputadora(computadora);
        entidad.setSubtotal(computadora.getTipo().getPrecio());
    }

    private void validarComputadora(Computadora computadora) throws InvalidDataException {
        if (!computadora.estaALaVenta()) {
            throw new InvalidDataException("la computadora con el id: '" + computadora.getIdComputadora() +"' "
                    + "no esta disponible para comprar");
        }
    }

    @Override
    public void eliminarEntidad(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        entidad = new DetalleCompra();
        entidad.setCompra(compra);
        ComputadoraCRUD computadora = new ComputadoraCRUD();
        computadora.setIdNombre("idComputadora");
        entidad.setComputadora(computadora.obtenerEntidad(request));
        repositorio.eliminar(entidad);
    }
    
    public void setCompra(Compra compra) {
        this.compra = compra;
    }

}
